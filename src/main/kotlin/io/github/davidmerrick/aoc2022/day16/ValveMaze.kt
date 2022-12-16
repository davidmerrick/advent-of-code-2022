package io.github.davidmerrick.aoc2022.day16

class ValveMaze(val valves: List<Valve>) {

    fun optimalRoute(): Int {
        TODO()
    }


    companion object {
        fun of(lines: List<String>): ValveMaze {
            return ValveMaze(lines.map { Valve.of(it) })
        }
    }
}
