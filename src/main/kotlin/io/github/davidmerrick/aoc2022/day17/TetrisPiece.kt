package io.github.davidmerrick.aoc2022.day17

import io.github.davidmerrick.aoc.coordinates.LongPos

data class TetrisPiece(val value: String) {

    private val rows = value.split("\n")
    val width = rows.first().length

    /**
     * Compute the positions the piece occupies,
     * relative to current.
     * Note that curPos is the bottom-left position of the piece.
     */
    fun computePositions(curPos: LongPos): List<LongPos> {
        return buildList {
            rows.reversed().forEachIndexed { y, row ->
                row.forEachIndexed { x, char ->
                    if (char == '#') add(curPos.copy(x = curPos.x + x, y = curPos.y + y))
                }
            }
        }
    }
}
