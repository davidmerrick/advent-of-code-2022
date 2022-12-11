package io.github.davidmerrick.aoc2022.day9

import com.google.common.collect.HashBasedTable
import io.github.davidmerrick.aoc.coordinates.Move
import io.github.davidmerrick.aoc.coordinates.Pos
import io.github.davidmerrick.aoc.coordinates.chebyshevDistance
import io.github.davidmerrick.aoc.coordinates.plus
import io.github.davidmerrick.aoc.guava.fill
import io.github.davidmerrick.aoc.guava.print

class Rope(segmentCount: Int = 2) {

    private val visited = mutableSetOf(Pos(0, 0))
    private val segments = MutableList(segmentCount) { Pos(0, 0) }

    val visitedCount
        get() = visited.size

    fun swing(moves: List<Move>) {
        for (move in moves) {
            segments[0] = segments[0] + move
            for ((headIndex, tailIndex) in segments.indices.zipWithNext()) {
                segments[tailIndex] = segments[tailIndex] + computeTailMove(segments[headIndex], segments[tailIndex])
            }
            visited += segments.last()
        }
    }

    private fun computeTailMove(headPos: Pos, tailPos: Pos): Move {
        return if (tailPos.chebyshevDistance(headPos) > 1) {
            Move(
                (headPos.x - tailPos.x).coerceIn(-1, 1),
                (headPos.y - tailPos.y).coerceIn(-1, 1)
            )
        } else Move(0, 0)
    }

    fun print(): String {
        val table = HashBasedTable.create<Int, Int, Char>()
        table.fill(visited.maxOf { it.x }, visited.maxOf { it.y }, '.')
        visited.forEach {
            table.put(it.y, it.x, '#')
        }
        return table.print()
    }
}
