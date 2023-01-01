package io.github.davidmerrick.aoc2022.day16

import com.github.shiguruikai.combinatoricskt.permutations
import io.github.davidmerrick.aoc.collections.set

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
    private fun shortestPaths(): Map<String, Map<String, Int>> {
        val shortestPaths = valves.values.associate {
            it.id to it.tunnels.associateWith { 1 }.toMutableMap()
        }.toMutableMap()

        shortestPaths.keys.permutations(3).forEach { (waypoint, from, to) ->
            shortestPaths[from, to] = minOf(
                shortestPaths[from, to], // Existing Path
                shortestPaths[from, waypoint] + shortestPaths[waypoint, to] // New Path
            )
        }
        val zeroFlowRooms = valves.values.filter { it.flowRate == 0 || it.id == "AA" }
            .map { it.id }
            .toSet()
        shortestPaths.values.forEach { it.keys.removeAll(zeroFlowRooms) }
        val canGetToFromAA: Set<String> = shortestPaths.getValue("AA").keys
        return shortestPaths.filter { it.key in canGetToFromAA || it.key == "AA" }
    }


    companion object {
        fun of(lines: List<String>) = lines.map { Valve.of(it) }
            .associateBy { it.id }
            .let { ValveMaze(it) }
    }
}

private operator fun Map<String, Map<String, Int>>.get(key1: String, key2: String, defaultValue: Int = 31000): Int =
    get(key1)?.get(key2) ?: defaultValue
