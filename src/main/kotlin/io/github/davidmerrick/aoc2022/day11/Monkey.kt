package io.github.davidmerrick.aoc2022.day11

data class Monkey(
    val id: Int,
    val items: MutableList<Long>,
    val operation: (Long) -> Long,
    val test: Test
) {
    private var _inspectedCount = 0L
    val inspectedCount
        get() = _inspectedCount

    fun inspect(item: Long): Long {
        _inspectedCount++
        return operation(item)
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

        private fun parseItems(lines: List<String>): MutableList<Long> {
            return lines[1]
                .trim()
                .replace("Starting items: ", "")
                .split(", ")
                .map { it.toLong() }
                .toMutableList()
        }

        private fun parseTest(lines: List<String>): Test {
            val (divisibleBy, ifTrue, ifFalse) = lines.takeLast(3)
                .map { it.trim() }
                .map { it.split(" ").last().toInt() }
            return Test(divisibleBy, ifTrue, ifFalse)
        }

        private fun parseOperation(lines: List<String>): (Long) -> Long {
            val (a, operator, b) = lines[2]
                .trim()
                .replace("Operation: ", "")
                .split(" = ")[1]
                .split(" ")
            return when (operator) {
                "+" -> { old -> (a.toLongOrNull() ?: old) + (b.toLongOrNull() ?: old) }
                "*" -> { old -> (a.toLongOrNull() ?: old) * (b.toLongOrNull() ?: old) }
                else -> error(operator)
            }
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
