package io.github.davidmerrick.aoc2022.day11

data class Monkey(
    val id: Int,
    val startingItems: List<Int>,
    val operation: Operation,
    val test: Test
) {
    companion object {
        fun of(str: String): Monkey {
            val lines = str.split("\n")

            return Monkey(
                id = parseId(lines),
                startingItems = parseStartingItems(lines),
                operation = parseOperation(lines),
                test = parseTest(lines)
            )
        }

        private fun parseStartingItems(lines: List<String>): List<Int> {
            return lines[1]
                .trim()
                .replace("Starting items: ", "")
                .split(", ")
                .map { it.toInt() }
                .toList()
        }

        private fun parseTest(lines: List<String>): Test {
            // Relevant values are all at the end, so just grab those
            val values = lines.takeLast(3)
                .map { it.trim() }
                .map { it.split(" ").last().toInt() }
            return Test(values[0], values[1], values[2])
        }

        private fun parseOperation(lines: List<String>): Operation {
            val split = lines[2]
                .trim()
                .replace("Operation: ", "")
                .split(" = ")[1]
                .split(" ")
            return Operation(
                a = split[0],
                b = split[2],
                operator = split[1]
            )
        }

        private fun parseId(lines: List<String>): Int {
            return lines[0].split(" ", ":")[1].toInt()
        }
    }
}

data class Test(
    val divisibleBy: Int,
    val ifTrue: Int,
    val ifFalse: Int
)

data class Operation(
    val a: String,
    val b: String,
    val operator: String
)
