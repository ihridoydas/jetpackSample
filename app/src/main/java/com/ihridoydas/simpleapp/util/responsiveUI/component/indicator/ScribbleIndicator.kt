package com.ihridoydas.simpleapp.util.responsiveUI.component.indicator

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.PathMeasure
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ihridoydas.simpleapp.ui.theme.SimpleAppTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlin.math.ceil
import kotlin.math.floor

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ScribbleIndicator() {

    Column(
        modifier = Modifier
            .padding(vertical = 64.dp)
            .fillMaxSize(),
    ) {

        val scope = rememberCoroutineScope()
        val pagerState = rememberPagerState()

        val sizeList = remember { mutableStateMapOf<Int, Pair<Float, Float>>() }

        val progressFromFirstPage by remember {
            derivedStateOf {
                pagerState.currentPageOffsetFraction
            }
        }

        ScrollableTabRow(
            selectedTabIndex = pagerState.currentPage,
            edgePadding = 20.dp,
            backgroundColor = Color.Transparent,
            contentColor = Color(0xFF362C28),
            divider = {},
            indicator = {
                Box(modifier = Modifier
                    .padding(start = 20.dp)
                    .fillMaxSize()
                    .drawBehind {

                        val ribbonSectionsLengths = mutableMapOf<Int, Float>()
                        var currentRibbonLength = 0f

                        var currentOrigin = 0f
                        val path = Path()

                        sizeList.keys
                            .sorted()
                            .mapNotNull { sizeList[it] }
                            .forEachIndexed { index, (width, height) ->

                                val bottom = height - 10f
                                val top = 10f

                                if (index == 0) path.moveTo(0f, top)

                                path.quadraticBezierTo(
                                    currentOrigin + width,
                                    top,
                                    currentOrigin + width,
                                    height / 2,
                                )

                                path.quadraticBezierTo(
                                    currentOrigin + width,
                                    bottom,
                                    currentOrigin + (width / 2),
                                    bottom,
                                )

                                path.quadraticBezierTo(
                                    currentOrigin + 0f,
                                    bottom,
                                    currentOrigin + 0f,
                                    height / 2,
                                )

                                path.quadraticBezierTo(
                                    currentOrigin,
                                    top,
                                    currentOrigin + width,
                                    top,
                                )

                                currentOrigin += width

                                val measure = PathMeasure()
                                measure.setPath(path, false)

                                val length = measure.length
                                ribbonSectionsLengths[index] = length - currentRibbonLength
                                currentRibbonLength = length
                            }

                        val progress = progressFromFirstPage - floor(progressFromFirstPage)
                        val start = floor(progressFromFirstPage)
                            .toInt()
                            .coerceIn(0, ribbonSectionsLengths.size - 1)
                        val end = ceil(progressFromFirstPage)
                            .toInt()
                            .coerceIn(0, ribbonSectionsLengths.size - 1)

                        val ribbonLength =
                            ribbonSectionsLengths[start]!! + ((ribbonSectionsLengths[end]!! - ribbonSectionsLengths[start]!!) * progress)

                        val lengthUntilStart = ribbonSectionsLengths
                            .keys
                            .sorted()
                            .map { ribbonSectionsLengths[it] ?: 0f }
                            .take(start)
                            .fold(0f) { acc, it -> acc - it }

                        val lengthUntilEnd = ribbonSectionsLengths
                            .keys
                            .sorted()
                            .map { ribbonSectionsLengths[it] ?: 0f }
                            .take(end)
                            .fold(0f) { acc, it -> acc - it }

                        val phaseOffset =
                            lengthUntilStart + ((lengthUntilEnd - lengthUntilStart) * progress)

                        drawPath(
                            path = path,
                            color = Color(0xFF7A6151),
                            style = Stroke(
                                width = 20f,
                                cap = StrokeCap.Round,
                                join = StrokeJoin.Round,
                                pathEffect = PathEffect.dashPathEffect(
                                    intervals = floatArrayOf(
                                        ribbonLength, currentRibbonLength
                                    ),
                                    phase = phaseOffset,
                                )
                            )
                        )
                    }
                )
            }
        ) {
            list.forEachIndexed { index, recipe ->
                Tab(
                    selected = index == pagerState.currentPage,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    modifier = Modifier
                        .onSizeChanged {
                            sizeList[index] = Pair(it.width.toFloat(), it.height.toFloat())
                        },
                    interactionSource = remember { NoInteraction() }
                ) {
                    Text(
                        text = recipe.title.uppercase(),
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                        ),
                        modifier = Modifier
                            .align(CenterHorizontally)
                            .padding(horizontal = 32.dp, vertical = 16.dp)
                    )
                }
            }
        }

        PagerContent(pagerState = pagerState, list = list)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PagerContent(pagerState: androidx.compose.foundation.pager.PagerState, list: List<Recipe>) {
    HorizontalPager(
        pageCount = list.size,
        state = pagerState,
        beyondBoundsPageCount = 10,
    ) { page ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp, vertical = 24.dp)
        ) {
            AsyncImage(
                model = list[page].imgURL,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .aspectRatio(3 / 2f)
                    .fillMaxWidth()
                    .offset(20.dp, 20.dp)
                    .background(Color(0xFFDAAD90), shape = RoundedCornerShape(20.dp))
                    .offset((-20).dp, (-20).dp)
                    .clip(RoundedCornerShape(20.dp))
            )

            Box(Modifier.height(30.dp))

            Text(
                text = list[page].title,
                fontWeight = FontWeight.Black,
                fontSize = 42.sp,
                color = Color(0xFF362C28)
            )
            Box(Modifier.height(7.dp))
            Text(text = list[page].description)
        }
    }
}


@Stable
private class NoInteraction : MutableInteractionSource {
    override val interactions: Flow<Interaction> = MutableSharedFlow()
    override suspend fun emit(interaction: Interaction) {}
    override fun tryEmit(interaction: Interaction) = false
}

data class Recipe(
    val title: String,
    val description: String,
    val imgURL: String
)

val list = listOf(
    Recipe(
        "Schnitzel",
        "A thin cutlet of meat (usually pork or veal) that is breaded and fried until crispy.",
        "https://www.familienkost.de/images/familienkostbild_1248_schnitzel.jpg"
    ),
    Recipe(
        "Sauerbraten",
        "A type of pot roast that is marinated in a mixture of vinegar, spices, and red wine for several days before being cooked.",
        "https://www.koch-mit.de/app/uploads/2020/03/sauerbraten.jpg"
    ),
    Recipe(
        "Kartoffelpuffer",
        "Also known as potato pancakes, these are made by shredding potatoes and mixing them with flour, eggs, and onions before frying them until crispy.",
        "https://www.gutekueche.at/storage/media/recipe/107893/conv/kartoffelpuffer-default.jpg"
    ),
    Recipe(
        "Spätzle",
        "A type of soft egg noodle that is typically served with a variety of dishes, such as roasted meats or stews.",
        "https://die-frau-am-grill.de/wp-content/uploads/spaetzle-rezept-istock.jpg"
    ),
    Recipe(
        "Rouladen",
        "Thinly sliced beef that is rolled up with onions, mustard, and bacon before being braised in a red wine sauce.",
        "https://www.globus.de/media/globus/mio/magazin/ausgaben-2022/dezember_2022/roullade_rotkohl_169.jpg"
    ),
    Recipe(
        "Königsberger Klopse",
        "Meatballs made from ground beef or veal that are simmered in a creamy white sauce with capers, lemon juice, and anchovies.",
        "https://www.malteskitchen.de/wp-content/uploads/2022/01/koenigsberger-klopse-blogpost-5167-500x500.jpg"
    ),
    Recipe(
        "Schwarzwälder Kirschtorte",
        "Also known as Black Forest Cake, this is a chocolate sponge cake that is layered with whipped cream and cherries before being topped with chocolate shavings and a splash of cherry brandy.",
        "https://img.chefkoch-cdn.de/rezepte/463131139405875/bilder/1369608/crop-960x720/schwarzwaelder-kirschtorte-super-easy.jpg"
    ),
    Recipe(
        "Leberknödel",
        "Liver dumplings made from ground liver, bread crumbs, and spices. They are typically served in a beef broth and accompanied by Spätzle or boiled potatoes.",
        "https://www.servus.com/storage/recipe/rezept-vorspeise-suppe-innereien-gebackene-leberknodel-suppe.jpg?impolicy=recipe_head"
    ),
    Recipe(
        "Labskaus",
        "A type of stew made from corned beef, onions, and potatoes that is typically served with pickled herring and beetroot.",
        "https://images.eatsmarter.de/sites/default/files/styles/max_size/public/labskaus-nach-hamburger-art-44811.jpg"
    ),
    Recipe(
        "Weißwurst",
        "A type of sausage that is made from veal and pork and flavored with parsley, lemon, and cardamom. It is typically served with sweet mustard, pretzels, and a glass of beer.",
        "https://metzgereiwendel.de/wp-content/uploads/2015/11/Weisswurst.jpg"
    )
)


@Preview(showBackground = true)
@Composable
fun ScribbleIndicatorScreen() {
    SimpleAppTheme {
        //Not Work Properly [https://www.sinasamaki.com/custom-tabrow-indicator-in-jetpack-compose/]
        ScribbleIndicator()
    }


}