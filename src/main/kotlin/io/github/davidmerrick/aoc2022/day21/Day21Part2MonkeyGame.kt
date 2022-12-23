package io.github.davidmerrick.aoc2022.day21

class Day21Part2MonkeyGame(private val startingMonkeys: List<Day21Monkey>) {
    private val rootMonkeys = startingMonkeys.first { it.id == "root" }.listensTo

    fun findInput(): Long {
        return (0..100_000_000_000_000L).first { checkValue(it) }
    }

    fun checkValue(value: Long): Boolean {
        val ctx = startingMonkeys
            .associate { it.id to null }
            .toMutableMap<String, Long?>()
        while (ctx[rootMonkeys.first()] == null && ctx[rootMonkeys.last()] == null) {
            playRound(ctx, startingMonkeys.replaceHuman(value))
        }
        return ctx[rootMonkeys.first()] == ctx[rootMonkeys.last()]
    }

    private fun playRound(ctx: MutableMap<String, Long?>, monkeys: List<Day21Monkey>) {
        monkeys.filter { ctx[it.id] == null }
            .forEach { ctx[it.id] = it.operation(ctx) }
    }
}

fun List<Day21Monkey>.replaceHuman(value: Long): List<Day21Monkey> {
    return this.toMutableList()
        .apply {
            removeIf { it.id == "humn" }
            add(Day21Monkey("humn", { value }, emptyList()))
        }
        .toList()
}
