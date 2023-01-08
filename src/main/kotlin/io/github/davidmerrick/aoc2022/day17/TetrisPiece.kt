package io.github.davidmerrick.aoc2022.day17

import io.github.davidmerrick.aoc.coordinates.LongPos
import io.github.davidmerrick.aoc.coordinates.plus

class TetrisPiece(value: String) {

    val width: Int
    private val positions: Set<LongPos>

    init {
        val rows = value.split("\n")
        width = rows.first().length

        // Initialize positions
        positions = buildSet {
            rows.reversed().forEachIndexed { y, row ->
                row.forEachIndexed { x, char ->
                    if (char == '#') add(LongPos(x.toLong(), y.toLong()))
                }
            }
        }
    }

    /**
     * Compute the positions the piece occupies,
     * relative to current.
     */
    fun computePositions(curPos: LongPos) = positions.map { curPos + it }.toSet()
}
