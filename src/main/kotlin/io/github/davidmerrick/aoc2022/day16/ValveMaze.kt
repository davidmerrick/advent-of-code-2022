package io.github.davidmerrick.aoc2022.day16

import kotlin.random.Random

class ValveMaze(private val valves: List<Valve>) {

    fun optimalRoute(): Int {
        var minute = 0

        // Map of when a valve was opened to its flow rate
        val opened = mutableMapOf<Int, Int>()

        var currentValve = valves.first { it.id == "AA" }
        while (minute < 30) {
            with(currentValve) {
                // Prioritize opening the next best flow rate
                val topFlowRates = valves
                    .asSequence()
                    .filterNot { it.isOpen }
                    .map { it.flowRate }
                    .distinct()
                    .sortedByDescending { it }
                    .toList()
                if (!isOpen && flowRate in topFlowRates) {
                    isOpen = true
                    minute++
                    opened[minute] = flowRate
                }

                val moves = tunnels
                    .map { id -> valves.first { it.id == id } }
                // Check if there are any unopened ones
                // Otherwise just go to a random tunnel
                currentValve = moves
                    .filterNot { it.isOpen }
                    .maxByOrNull { it.flowRate } ?: moves[Random.nextInt() % moves.size]
            }

            minute++
        }
        return opened.map { (30 - it.key) * it.value }.sum()
    }


    companion object {
        fun of(lines: List<String>): ValveMaze {
            return ValveMaze(lines.map { Valve.of(it) })
        }
    }
}
