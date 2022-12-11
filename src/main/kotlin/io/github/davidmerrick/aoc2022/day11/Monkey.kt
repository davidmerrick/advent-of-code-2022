package io.github.davidmerrick.aoc2022.day11

data class Monkey(
    val id: Int,
    val items: MutableList<ULong>,
    val operation: Operation,
    val test: Test
) {
    private var _inspectedCount = 0UL
    val inspectedCount
        get() = _inspectedCount

    fun inspect(item: ULong): ULong {
        _inspectedCount += 1UL
        val a = if (operation.a == "old") item else operation.a.toULong()
        val b = if (operation.b == "old") item else operation.b.toULong()
        return when (operation.operator) {
            "+" -> a + b
            "-" -> a - b
            "*" -> a * b
            "/" -> a / b
            else -> throw RuntimeException("Invalid operator ${operation.operator}")
        }
    }

    companion object {
        fun of(str: String): Monkey {
            val lines = str.split("\n")

            return Monkey(
                id = parseId(lines),
                items = parseItems(lines),
                operation = parseOperation(lines),
                test = parseTest(lines)
            )
        }

        private fun parseItems(lines: List<String>): MutableList<ULong> {
            return lines[1]
                .trim()
                .replace("Starting items: ", "")
                .split(", ")
                .map { it.toULong() }
                .toMutableList()
        }

        private fun parseTest(lines: List<String>): Test {
            // Relevant values are all at the end, so just grab those
            val values = lines.takeLast(3)
                .map { it.trim() }
                .map { it.split(" ").last().toInt() }
            return Test(values[0].toULong(), values[1], values[2])
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
    val divisibleBy: ULong,
    val ifTrue: Int,
    val ifFalse: Int
)

data class Operation(
    val a: String,
    val b: String,
    val operator: String
)
