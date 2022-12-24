package io.github.davidmerrick.aoc2022.day21

class MonkeyGame(private val monkeys: List<Monkey>) {

    fun play() = monkeys.getRoot().yell()

    companion object {
        fun of(input: List<String>): MonkeyGame {
            val monkeys = input.map { Monkey(it) }
            monkeys.filterIsInstance<FormulaMonkey>().forEach { monkey ->
                monkey.left = monkeys.first { it.id == monkey.leftId }
                monkey.right = monkeys.first { it.id == monkey.rightId }
            }
            return MonkeyGame(monkeys)
        }
    }
}

fun List<Monkey>.getRoot() = this.first { it.id == "root" }
