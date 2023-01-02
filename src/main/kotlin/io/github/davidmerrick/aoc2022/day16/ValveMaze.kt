package io.github.davidmerrick.aoc2022.day16

import com.github.shiguruikai.combinatoricskt.permutations
import com.google.common.collect.HashBasedTable
import io.github.davidmerrick.aoc.guava.asSequence
import io.github.davidmerrick.aoc.guava.toTable

class ValveMaze(private val valves: Map<String, Valve>) {

    val shortestPaths by lazy { shortestPaths() }

    fun searchPaths(
        location: String,
        timeAllowed: Int,
        seen: Set<String> = emptySet(),
        timeTaken: Int = 0,
        totalFlow: Int = 0
    ): Int = shortestPaths
        .getValue(location)
        .asSequence()
        .filterNot { (nextRoom, _) -> nextRoom in seen }
        .filter { (_, traversalCost) -> timeTaken + traversalCost + 1 < timeAllowed }
        .maxOfOrNull { (nextRoom, traversalCost) ->
            searchPaths(
                nextRoom,
                timeAllowed,
                seen + nextRoom,
                timeTaken + traversalCost + 1,
                totalFlow + ((timeAllowed - timeTaken - traversalCost - 1) * valves.getValue(nextRoom).flowRate)
            )
        } ?: totalFlow

    /**
     * Compute shortest path from a room to every other
     */
    private fun shortestPaths(): HashBasedTable<String, String, Int> {
        val shortestPaths = HashBasedTable.create<String, String, Int>()
        valves.values.forEach {
            shortestPaths.put(it.id, it.id, 1)
        }

        shortestPaths.rows.permutations(3).forEach { (waypoint, from, to) ->
            shortestPaths.put(
                from, to, minOf(
                    shortestPaths.get(from, to) ?: 31_000, // Existing Path
                    shortestPaths.get(from, waypoint) + shortestPaths.get(waypoint, to) // New Path
                )
            )
        }
        val zeroFlowRooms = valves.values.filter { it.flowRate == 0 || it.id == "AA" }
            .map { it.id }
            .toSet()
        shortestPaths.rowKeySet().forEach { it.keys.removeAll(zeroFlowRooms) }
        val canGetToFromAA: Set<String> = shortestPaths.getValue("AA").keys
        return shortestPaths
            .asSequence()
            .filter { it.row in canGetToFromAA || it.row == "AA" }
            .toTable()
    }


    companion object {
        fun of(lines: List<String>) = lines.map { Valve.of(it) }
            .associateBy { it.id }
            .let { ValveMaze(it) }
    }
}
