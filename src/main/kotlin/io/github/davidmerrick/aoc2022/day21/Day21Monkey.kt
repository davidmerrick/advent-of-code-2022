package io.github.davidmerrick.aoc2022.day21

data class Day21Monkey(
    val id: String,
    val operation: String
) {
    companion object {
        fun of(line: String): Day21Monkey {
            val split = line.split(":")
                .map { it.trim() }
            return Day21Monkey(
                id = split[0],
                operation = split[1]
            )
        }
    }
}
