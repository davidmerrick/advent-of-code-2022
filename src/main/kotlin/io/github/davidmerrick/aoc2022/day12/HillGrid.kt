package io.github.davidmerrick.aoc2022.day12

import com.google.common.collect.HashBasedTable
import io.github.davidmerrick.aoc.collections.toCharRows
import io.github.davidmerrick.aoc.coordinates.Pos
import io.github.davidmerrick.aoc.guava.asSequence
import io.github.davidmerrick.aoc.guava.getNeighbors
import io.github.davidmerrick.aoc.guava.parseTable

class HillGrid(private val table: HashBasedTable<Int, Int, Char>) {

    // Map of possible steps from each location
    private val nodes: Map<Pos, Set<Pos>>
    private val visited: MutableMap<Pos, Long> = mutableMapOf()

    init {
        // Compute possible steps from each location
        nodes = buildMap {
            table.asSequence()
                .map { Pos(it.column, it.row) }
                .forEach { this[it] = getPossibleSteps(it) }
        }
    }

    private fun getPossibleSteps(pos: Pos): Set<Pos> {
        val value = table.get(pos.y, pos.x)
        return buildSet {
            table.getNeighbors(pos)
                .forEach {
                    if (elevationMap[it.value]!! <= elevationMap[value]!! + 1) {
                        add(Pos(it.column, it.row))
                    }
                }
        }
    }

    private fun distinctPaths(source: Pos, dest: Pos): Long {
        if (source == dest) return 1L

        if (!visited.containsKey(source)) {
            visited[source] = nodes[source]!!.sumOf { distinctPaths(it, dest) }
        }

        return visited[source]!!
    }

    companion object {
        fun of(lines: List<String>): HillGrid {
            return HillGrid(parseTable(lines.toCharRows()))
        }

        private val elevationMap = ('a'..'z').mapIndexed { i, value -> value to i }.toMap()
    }
}
