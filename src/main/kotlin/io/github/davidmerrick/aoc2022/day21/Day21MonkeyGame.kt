package io.github.davidmerrick.aoc2022.day21

class Day21MonkeyGame(input: List<Day21Monkey>) {

    private val monkeys = input.toMutableList()
    private val ctx = monkeys
        .associate { it.id to null }
        .toMutableMap<String, Long?>()

    fun play(): Long {
        while (ctx["root"] == null) {
            playRound()
        }
        return ctx["root"]!!
    }

    private fun playRound() {
        monkeys.filter { ctx[it.id] == null }
            .forEach {
                ctx[it.id] = it.operation(ctx)
            }
    }
}
