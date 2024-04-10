package com.ihridoydas.simpleapp.features.funWithUI.components.tictactoe

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathMeasure
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

data class PlayerGameItem(
    val centerX: Float,
    val centerY: Float,
    val player: Player,
    val animatable: Animatable<Float, AnimationVector1D>
)

data class Cell(
    val rect: Rect,
    var player: Player?
)

sealed class Player(val color: Color, val char: Char) {
    object Player1 : Player(color = Color.Green, char = 'O')
    object Player2 : Player(color = Color.Red, char = 'X')
}

@Composable
fun TicTacToe(
    size: Dp,
    showWinner: (player: Player?) -> Unit,
    onNewRound: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val coroutineScope = rememberCoroutineScope()

    var isPlaying by remember { mutableStateOf(true) }
    var rectOffset by remember { mutableStateOf(Offset.Zero) }
    var sizeInPx by remember { mutableStateOf(0f) }
    var cells by remember { mutableStateOf<Array<Array<Cell>>?>(null) }
    val results = remember { mutableStateListOf<PlayerGameItem>() }

    var currentPlayer by remember { mutableStateOf<Player>(Player.Player1) }

    LaunchedEffect(key1 = sizeInPx) {
        cells = initGameBoard(sizeInPx, rectOffset)
    }

    Canvas(modifier = modifier
        .pointerInput(true) {
            detectTapGestures { offset ->
                val gameRect =
                    Rect(
                        offset = rectOffset,
                        size = Size(size.toPx(), size.toPx())
                    )

                if (gameRect.contains(offset) && isPlaying) {
                    cells?.forEach { array ->
                        array.forEach { cell ->
                            if (cell.rect.contains(offset) && cell.player == null) {
                                val position = PlayerGameItem(
                                    centerX = cell.rect.center.x,
                                    centerY = cell.rect.center.y,
                                    player = currentPlayer,
                                    animatable = Animatable(0f)
                                )

                                results.add(position)
                                cell.player = currentPlayer

                                currentPlayer = if (currentPlayer == Player.Player1)
                                    Player.Player2
                                else Player.Player1

                                coroutineScope.launch {
                                    position.animatable.animateTo(
                                        targetValue = 1f,
                                        animationSpec = tween(
                                            durationMillis = 1000
                                        )
                                    )
                                }
                                cells?.let {
                                    updateGameState(
                                        cells = it,
                                        showWinner = { player ->
                                            showWinner(player)
                                            isPlaying = false
                                            coroutineScope.launch {
                                                delay(5000)
                                                cells = initGameBoard(sizeInPx, rectOffset)
                                                isPlaying = true
                                                onNewRound()
                                                results.clear()
                                            }
                                        }
                                    )
                                }

                                return@detectTapGestures
                            }
                        }
                    }
                }
            }
        }
    ) {
        val halfSize = size.toPx() / 2
        val distanceBetweenLines = size.toPx() / 6
        val center = this.center
        val topLeftOffset = Offset(center.x - halfSize, y = center.y - halfSize)

        rectOffset = topLeftOffset
        sizeInPx = size.toPx()

        results.forEach {
            if (it.player == Player.Player2) {
                val path1 = Path().apply {
                    moveTo(it.centerX - 50f, it.centerY - 50f)
                    lineTo(it.centerX + 50f, it.centerY + 50)
                }
                val path2 = Path().apply {
                    moveTo(it.centerX - 50f, it.centerY + 50f)
                    lineTo(it.centerX + 50f, it.centerY - 50f)
                }

                val outPath1 = Path()
                PathMeasure().apply {
                    setPath(path1, false)
                    getSegment(0f, it.animatable.value * length, outPath1, true)
                }
                val outPath2 = Path()
                PathMeasure().apply {
                    setPath(path2, false)
                    getSegment(0f, it.animatable.value * length, outPath2, true)
                }

                drawPath(
                    outPath1,
                    color = Color.Red,
                    style = Stroke(
                        width = 5.dp.toPx(),
                        cap = StrokeCap.Round
                    )
                )
                drawPath(
                    outPath2,
                    color = Color.Red,
                    style = Stroke(
                        width = 5.dp.toPx(),
                        cap = StrokeCap.Round
                    )
                )
            } else {
                val circle = Path().apply {
                    addOval(Rect(center = Offset(it.centerX, it.centerY), radius = 50f))
                }

                val outPath = Path()
                PathMeasure().apply {
                    setPath(circle, false)
                    getSegment(0f, it.animatable.value * length, outPath, true)
                }

                drawPath(
                    path = outPath,
                    color = Color.Green,
                    style = Stroke(
                        width = 5.dp.toPx(),
                        cap = StrokeCap.Round
                    )
                )
            }
        }
        drawPath(
            Path().apply {
                moveTo(center.x - halfSize, center.y - distanceBetweenLines)
                lineTo(center.x + halfSize, center.y - distanceBetweenLines)
            },
            color = Color.Black,
            style = Stroke(
                width = 5.dp.toPx(),
                cap = StrokeCap.Round
            )
        )
        drawPath(
            Path().apply {
                moveTo(center.x - halfSize, center.y + distanceBetweenLines)
                lineTo(center.x + halfSize, center.y + distanceBetweenLines)
            },
            color = Color.Black,
            style = Stroke(
                width = 5.dp.toPx(),
                cap = StrokeCap.Round
            )
        )
        drawPath(
            Path().apply {
                moveTo(center.x - distanceBetweenLines, center.y - halfSize)
                lineTo(center.x - distanceBetweenLines, center.y + halfSize)
            },
            color = Color.Black,
            style = Stroke(
                width = 5.dp.toPx(),
                cap = StrokeCap.Round
            )
        )
        drawPath(
            Path().apply {
                moveTo(center.x + distanceBetweenLines, center.y - halfSize)
                lineTo(center.x + distanceBetweenLines, center.y + halfSize)
            },
            color = Color.Black,
            style = Stroke(
                width = 5.dp.toPx(),
                cap = StrokeCap.Round
            )
        )
    }
}

fun initGameBoard(size: Float, rectOffset: Offset): Array<Array<Cell>> {

    val oneThird = size / 3
    return Array(3) { i ->
        Array(3) { j ->
            Cell(
                player = null,
                rect = Rect(
                    Offset(
                        x = rectOffset.x + j * oneThird,
                        y = rectOffset.y + i * oneThird
                    ),
                    Size(oneThird, oneThird)
                )
            )
        }
    }
}

fun updateGameState(cells: Array<Array<Cell>>, showWinner: (Player?) -> Unit) {
    if (cells[0][0].player != null
        && cells[0][0].player == cells[0][1].player
        && cells[0][0].player == cells[0][2].player
    ) {
        showWinner(cells[0][0].player!!)
    }

    if (cells[1][0].player != null
        && cells[1][0].player == cells[1][1].player
        && cells[1][0].player == cells[1][2].player
    ) {
        showWinner(cells[1][0].player!!)
    }

    if (cells[2][0].player != null
        && cells[2][0].player == cells[2][1].player
        && cells[2][0].player == cells[2][2].player
    ) {
        showWinner(cells[2][0].player!!)
    }

    if (cells[0][0].player != null
        && cells[0][0].player == cells[1][0].player
        && cells[0][0].player == cells[2][0].player
    ) {
        showWinner(cells[0][0].player!!)
    }

    if (cells[0][1].player != null
        && cells[0][1].player == cells[1][1].player
        && cells[0][1].player == cells[2][1].player
    ) {
        showWinner(cells[0][1].player!!)
    }

    if (cells[0][2].player != null
        && cells[0][2].player == cells[1][2].player
        && cells[0][2].player == cells[2][2].player
    ) {
        showWinner(cells[0][2].player!!)
    }

    if (cells[0][0].player != null
        && cells[1][1].player == cells[0][0].player
        && cells[2][2].player == cells[0][0].player
    ) {
        showWinner(cells[0][0].player!!)
    }

    if (cells[0][2].player != null
        && cells[0][2].player == cells[1][1].player
        && cells[0][2].player == cells[2][0].player
    ) {
        showWinner(cells[0][2].player!!)
    }

    val players = cells.flatMap { it.map { cell -> cell.player } }
    if (players.all { it != null})
        showWinner(null)
}