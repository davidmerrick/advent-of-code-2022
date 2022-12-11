package io.github.davidmerrick.aoc2022.day10

object CathodeCpu {

    /**
     * Returns an Int representing the signal strength
     */
    fun execute(instructions: List<String>): Int {
        return cycleSequence(instructions)
            .filter { it.first in (20..220 step 40) }
            .sumOf { it.first * it.second }
    }

    fun draw(instructions: List<String>): String {
        // Map the cycle to the X value
        val cycles = cycleSequence(instructions).toList()

        return buildString {
            for (cycle in cycles) {
                val position = cycle.first % 40
                if (position == 1) {
                    append("\n")
                }

                val spriteRange = (cycle.second..cycle.second + 2)
                if(position in spriteRange){
                    append("#")
                } else append(".")
            }
        }.trim()
    }

    /**
     * Returns a sequence of pairs of cycle value -> x
     */
    private fun cycleSequence(instructions: List<String>): Sequence<Pair<Int, Int>> {
        var x = 1
        var cycle = 0

        return buildMap {
            for (instruction in instructions) {
                if (instruction == "noop") {
                    cycle++
                    this[cycle] = x
                } else {
                    cycle++
                    this[cycle] = x
                    cycle++
                    this[cycle] = x
                    x += instruction.split(" ")[1].toInt()
                }
            }
        }.asSequence()
            .map { it.key to it.value }
    }
}
