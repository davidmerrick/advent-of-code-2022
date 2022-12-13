package io.github.davidmerrick.aoc2022.day12

import com.google.common.collect.HashBasedTable
import io.github.davidmerrick.aoc.collections.toCharRows
import io.github.davidmerrick.aoc.guava.parseTable
import io.github.davidmerrick.aoc.guava.shortestPath

class HillGrid(private val table: HashBasedTable<Int, Int, Char>) {

    /**
     * Use BFS to find the shortest path
     */
    fun shortestPath(
        sourcePredicate: (Char) -> Boolean = { it == 'S' },
        destinationPredicate: (Char) -> Boolean = { it == 'E' },
        isAdjacent: (Char, Char) -> Boolean = { node, neighbor -> neighbor.elevation() <= node.elevation() + 1 }
    ) = table.shortestPath(sourcePredicate, destinationPredicate, isAdjacent)

    companion object {
        fun of(lines: List<String>): HillGrid {
            return HillGrid(parseTable(lines.toCharRows()))
        }
    }
}

private val elevations = ('a'..'z').mapIndexed { i, value -> value to i }
    .toMap()
    .toMutableMap()
    .apply {
        put('S', 0)
        put('E', 26)
    }

fun Char.elevation() = elevations[this]!!
