package io.github.davidmerrick.aoc2022.day16

import com.github.shiguruikai.combinatoricskt.combinations
import com.github.shiguruikai.combinatoricskt.permutations
import io.github.davidmerrick.aoc.search.Bfs

class ValveMaze(private val valves: List<Valve>) : Bfs<String>() {

    /**
     * Use BFS to compute shortest path from a room to every other
     * Chose BFS because the path cost is 1 between all these
     */
    fun shortestPaths(): Map<String, Map<String, Int>> {
        return valves.map { it.id }
            .combinations(2)
            .map { it.first() to mapOf(it.last() to shortestPath(it.first(), it.last())) }
            .groupBy({ it.first }, { it.second })
            .map { entry ->
                entry.key to entry.value.flatMap { it.entries }.associate { it.key to it.value }
            }
            // Filter out zero flow-rate spots
            .filterNot { path ->
                val valve = valves.first { it.id == path.first }
                valve.flowRate == 0 && valve.id != "AA"
            }
            // Filter only places accessible from AA
            .filterNot { shortestPath("AA", it.first) == Int.MAX_VALUE }
            .toMap()
    }


    companion object {
        fun of(lines: List<String>): ValveMaze {
            return ValveMaze(lines.map { Valve.of(it) })
        }
    }

    override fun getAdjacent(input: String) = valves.first { it.id == input }.tunnels
}
