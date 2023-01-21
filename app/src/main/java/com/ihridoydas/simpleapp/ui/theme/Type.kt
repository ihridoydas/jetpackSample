package com.ihridoydas.simpleapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import com.ihridoydas.simpleapp.R

//Note
//Two way we can use font 1.custom 2.google font

//Use Downloadable Font (1.custom Font)
//This is the by Default for whole application
fun getTypography(fontFamily: FontFamily) = Typography(
   // defaultFontFamily = fontFamily
    displayLarge = TextStyle(
        //its possible another font use with this fontFamily
        fontFamily = fontFamily, //MontserratFontFamily
        fontWeight = FontWeight.Light,
        fontSize = 57.sp,
        lineHeight = 64.sp,
        letterSpacing = 0.sp
    ),
    displayMedium = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Light,
        fontSize = 45.sp,
        lineHeight = 52.sp,
        letterSpacing = 0.sp
    ),
    displaySmall = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 36.sp,
        lineHeight = 44.sp,
        letterSpacing = 0.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.sp
    ),
    titleLarge = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    titleMedium = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp
    ),
    titleSmall = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp
    ),
    bodySmall = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.4.sp
    ),
    labelLarge = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),
    labelMedium = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),
    labelSmall = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )

)
//Custom /res->font->
val fonts = FontFamily(
    Font(R.font.notosans_light,FontWeight.Light),
    Font(R.font.notosans_regular,FontWeight.Normal),
    Font(R.font.notosans_medium,FontWeight.Medium),
    Font(R.font.notosans_bold,FontWeight.Bold),
    Font(R.font.notosans_black,FontWeight.Black),
)

//-----------------------------***------------------------------

//Font Provider(2.google font) //we can use when UI needed
val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

//You can use multiple google font like this
val MontserratFont = GoogleFont(name = "Montserrat") //https://fonts.google.com/?query=Montserrat
val KarlaFont = GoogleFont(name = "Karla")

val MontserratFontFamily = FontFamily(
    Font(googleFont = MontserratFont, fontProvider = provider, weight = FontWeight.Black),
    Font(googleFont = MontserratFont, fontProvider = provider, weight = FontWeight.Bold),
    Font(googleFont = MontserratFont, fontProvider = provider, weight = FontWeight.SemiBold),
    Font(googleFont = MontserratFont, fontProvider = provider, weight = FontWeight.Normal),
    Font(googleFont = MontserratFont, fontProvider = provider, weight = FontWeight.Light),

)

val KarlaFontFamily = FontFamily(
    Font(googleFont = KarlaFont, fontProvider = provider),
    Font(googleFont = KarlaFont, fontProvider = provider, weight = FontWeight.Black),
    Font(googleFont = KarlaFont, fontProvider = provider, weight = FontWeight.Bold),
    Font(googleFont = KarlaFont, fontProvider = provider, weight = FontWeight.SemiBold),
    Font(googleFont = KarlaFont, fontProvider = provider, weight = FontWeight.Normal),
    Font(googleFont = KarlaFont, fontProvider = provider, weight = FontWeight.Light),
)

//Use This
    /*Text(
        text = "Jetpack Compose",
        fontFamily = KarlaFontFamily,
    )*/


