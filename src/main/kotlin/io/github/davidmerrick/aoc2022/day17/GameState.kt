package io.github.davidmerrick.aoc2022.day17

import io.github.davidmerrick.aoc.coordinates.LongPos

data class GameState(
    val jetIndex: Int,
    val piece: TetrisPiece,
    val board: Set<LongPos>
)
