package io.github.davidmerrick.aoc2022.day21

class MonkeyGame(monkeys: List<Monkey>) {

    private val root = monkeys.first { it.id == ROOT_ID }

    fun play() = root.yell()
    fun humanValue() = root.humanValue()

    companion object {
        fun of(input: List<String>): MonkeyGame {
            val monkeys = input.map { Monkey(it) }
            monkeys.filterIsInstance<OpMonkey>().forEach { monkey ->
                monkey.left = monkeys.first { it.id == monkey.leftId }
                monkey.right = monkeys.first { it.id == monkey.rightId }
            }
            return MonkeyGame(monkeys)
        }
    }
}
