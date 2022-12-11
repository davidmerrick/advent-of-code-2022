package io.github.davidmerrick.aoc2022.day11

import kotlin.math.floor

class MonkeyGame(
    private val monkeys: MutableList<Monkey>,
    private val worryModifier: (Long) -> Long = { floor(it.toDouble() / 3).toLong() }
) {
    val inspectedCounts
        get() = monkeys.map { it.id to it.inspectedCount }

    val monkeyBusiness: Long
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
                    val destination = if (newValue % divisibleBy.toLong() == 0L) {
                        ifTrue
                    } else ifFalse

                    throwTo(destination, newValue)
                }
            }
        }
    }

    private fun throwTo(id: Int, value: Long) {
        monkeys.first { it.id == id }.items.add(value)
    }
}
