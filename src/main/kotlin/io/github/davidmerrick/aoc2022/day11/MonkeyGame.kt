package io.github.davidmerrick.aoc2022.day11

import kotlin.math.floor

class MonkeyGame(
    private val monkeys: MutableList<Monkey>,
    private val worryModifier: (ULong) -> ULong = { floor(it.toDouble() / 3).toULong() }
) {
    val inspectedCounts
        get() = monkeys.map { it.id to it.inspectedCount }

    val monkeyBusiness: ULong
        get() = monkeys
            .map { it.inspectedCount }
            .sortedByDescending { it }
            .take(2)
            .reduce { a, b -> a * b }


    fun play(rounds: Int) {
        repeat(rounds) {
            playRound()
        }
    }

    private fun playRound() {
        for (monkey in monkeys) {
            while (monkey.items.isNotEmpty()) {
                val item = monkey.items.removeFirst()
                val newValue = worryModifier(monkey.inspect(item))

                // Test and throw
                with(monkey.test) {
                    val destination = if (newValue % divisibleBy == 0UL) {
                        ifTrue
                    } else ifFalse

                    throwTo(destination, newValue)
                }
            }
        }
    }

    private fun throwTo(id: Int, value: ULong) {
        monkeys.first { it.id == id }.items.add(value)
    }
}
