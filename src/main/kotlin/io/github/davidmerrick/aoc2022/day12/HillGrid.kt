package io.github.davidmerrick.aoc2022.day12

import com.google.common.collect.HashBasedTable
import io.github.davidmerrick.aoc.collections.toCharRows
import io.github.davidmerrick.aoc.coordinates.Pos
import io.github.davidmerrick.aoc.guava.asSequence
import io.github.davidmerrick.aoc.guava.getNeighbors
import io.github.davidmerrick.aoc.guava.parseTable
import io.github.davidmerrick.aoc.guava.pos

class HillGrid(private val table: HashBasedTable<Int, Int, Char>) {

    // Map of possible steps from each location
    private val adjacencyMap: Map<Pos, Set<Pos>>

    init {
        // Compute possible steps from each location
        adjacencyMap = buildMap {
            table.asSequence()
                .map { Pos(it.column, it.row) }
                .forEach { this[it] = getPossibleSteps(it) }
        }
    }

    private fun getStart(): Pos {
        return table.asSequence()
            .first { it.value == 'S' }
            .pos()
    }

    private fun getEnd(): Pos {
        return table.asSequence()
            .first { it.value == 'E' }
            .pos()
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

    /**
     * Use BFS to find the shortest path
     */
    private fun shortestPath(source: Pos, dest: Pos): Long {
        val visited: MutableMap<Pos, Long> = mutableMapOf()
        val end = getEnd()

        val queue = ArrayDeque<Pos>()
        queue.add(getStart())

        while (!queue.isEmpty()) {
            val currentNode = queue.removeFirst()

            if (currentNode == end) {
                break
            } else {
                queue.addAll(adjacencyMap[currentNode]!!)
            }
        }

    }

    companion object {
        fun of(lines: List<String>): HillGrid {
            return HillGrid(parseTable(lines.toCharRows()))
        }

        private val elevationMap by lazy {
            val eMap = ('a'..'z').mapIndexed { i, value -> value to i }
                .toMap()
                .toMutableMap()
            eMap['S'] = 0
            eMap['E'] = 26
            eMap
        }
    }
}
