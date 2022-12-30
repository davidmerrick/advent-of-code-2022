package io.github.davidmerrick.aoc2022.day16

import com.github.shiguruikai.combinatoricskt.permutations

class ValveMaze(private val valves: List<Valve>) {

    /**
     * Compute a map of each room to the shortest paths to every other room.
     */
    fun shortestPaths(): Map<String, Map<String, Int>> {
        // Use Djikstra's to compute shortest path from a room to every other

        TODO()
    }


    companion object {
        fun of(lines: List<String>): ValveMaze {
            return ValveMaze(lines.map { Valve.of(it) })
        }
    }
}
