package io.github.davidmerrick.aoc2022.day16

import com.github.shiguruikai.combinatoricskt.permutations
import com.google.common.collect.HashBasedTable
import io.github.davidmerrick.aoc.guava.asSequence
import io.github.davidmerrick.aoc.guava.toTable
import io.github.davidmerrick.aoc.guava.get

private const val DEFAULT_PATH_LENGTH = 31_000

class ValveMaze(private val valves: Map<String, Valve>) {

    val shortestPaths by lazy { shortestPaths() }

    fun searchPaths(
        location: String,
        timeAllowed: Int,
        seen: Set<String> = emptySet(),
        timeTaken: Int = 0,
        totalFlow: Int = 0
    ): Int = shortestPaths
        .row(location)
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
     * Result is a table containing rows of start, end, and distance
     */
    private fun shortestPaths(): HashBasedTable<String, String, Int> {
        val shortestPaths = HashBasedTable.create<String, String, Int>()

        // Shortest path to its neighbors is 1
        valves.values.forEach {
            it.neighbors.forEach { tunnel ->
                shortestPaths.put(it.id, tunnel, 1)
            }
        }

        shortestPaths.columnKeySet()
            .permutations(3)
            .forEach { (waypoint, from, to) ->
                shortestPaths.put(
                    from, to, minOf(
                        shortestPaths.get(from, to, DEFAULT_PATH_LENGTH)!!, // Existing path
                        shortestPaths.get(from, waypoint, DEFAULT_PATH_LENGTH)!! + shortestPaths.get(
                            waypoint,
                            to,
                            DEFAULT_PATH_LENGTH
                        )!! // New Path
                    )
                )
            }

        // Filter out rooms with a flow rate of 0 that are not AA
        valves.values.filter { it.flowRate == 0 || it.id == "AA" }
            .map { it.id }
            .toSet()
            .forEach {
                shortestPaths.remove(it, null)
            }

        // Filter only rooms accessible from "AA"
        val canGetToFromAA: Set<String> = shortestPaths.asSequence()
            .filter { it.column == "AA" }
            .map { it.row }
            .toSet()

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
