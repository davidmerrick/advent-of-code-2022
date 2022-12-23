package io.github.davidmerrick.aoc2022.day21

import io.github.davidmerrick.aoc2022.day13.isInt

data class Day21Monkey(
    val id: String,
    val operation: (Map<String, Long?>) -> Long?,
    val listensTo: List<String> = emptyList()
) {
    companion object {
        fun of(line: String): Day21Monkey {
            val split = line.split(":")
                .map { it.trim() }
            return Day21Monkey(
                id = split[0],
                operation = parseOperation(split[1]),
                listensTo = parseListensTo(split[1])
            )
        }

        private fun parseListensTo(str: String): List<String> {
            if (str.isInt()) return emptyList()
            val split = str.split(" ")
            return listOf(split[0], split[2])
        }

        private fun parseOperation(str: String): (Map<String, Long?>) -> Long? {
            if (str.isInt()) return { str.toLong() }
            return { ctx ->
                val split = str.split(" ")
                val a = ctx[split[0]]
                val b = ctx[split[2]]
                if (a != null && b != null) {
                    when (split[1]) {
                        "*" -> a * b
                        "-" -> a - b
                        "+" -> a + b
                        "/" -> a / b
                        else -> error(split[1])
                    }
                } else null
            }

        }
    }
}
