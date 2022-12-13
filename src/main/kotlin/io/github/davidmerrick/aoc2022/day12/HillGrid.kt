package io.github.davidmerrick.aoc2022.day12

import com.google.common.collect.HashBasedTable
import io.github.davidmerrick.aoc.collections.toCharRows
import io.github.davidmerrick.aoc.coordinates.Pos
import io.github.davidmerrick.aoc.guava.asSequence
import io.github.davidmerrick.aoc.guava.getEntry
import io.github.davidmerrick.aoc.guava.getNeighbors
import io.github.davidmerrick.aoc.guava.parseTable
import io.github.davidmerrick.aoc.guava.pos

class HillGrid(private val table: HashBasedTable<Int, Int, Char>) {

    private fun getAdjacent(pos: Pos): Set<Pos> {
        val value = table.get(pos.y, pos.x)
        return buildSet {
            table.getNeighbors(pos).forEach {
                if (elevationMap[it.value]!! <= elevationMap[value]!! + 1) {
                    add(Pos(it.column, it.row))
                }
            }
        }
    }

    /**
     * Use BFS to find the shortest path
     */
    fun shortestPath(
        sourcePredicate: (Char) -> Boolean, destinationPredicate: (Char) -> Boolean
    ): Int {
        val visited = mutableSetOf<Pos>()
        val start = table.asSequence().first { sourcePredicate(it.value) }.pos().let { Step(it, 0) }

        val queue = ArrayDeque<Step>().apply { add(start) }

        while (queue.isNotEmpty()) {
            val step = queue.removeFirst()
            visited.add(step.pos)

            if (destinationPredicate(table.getEntry(step.pos)!!.value)) {
                return step.distance
            } else {
                getAdjacent(step.pos)
                    .filterNot { visited.contains(it) }
                    .map { step.toward(it) }
                    .forEach { queue.addLast(it) }
            }
        }

        error("No path found matching predicate")
    }

    companion object {
        fun of(lines: List<String>): HillGrid {
            return HillGrid(parseTable(lines.toCharRows()))
        }

        private val elevationMap = ('a'..'z').mapIndexed { i, value -> value to i }
            .toMap()
            .toMutableMap()
            .apply {
                put('S', 0)
                put('E', 26)
            }
    }
}

data class Step(
    val pos: Pos, val
    distance: Int
) {
    fun toward(pos: Pos) = this.copy(pos = pos, distance = distance + 1)
}
