package io.github.davidmerrick.aoc2022.day11

import io.github.davidmerrick.aoc.util.TestUtil.readText
import io.github.davidmerrick.aoc.util.lcm
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

class Day11Test {

    @Test
    fun `Part 1 example`() {
        val monkeyGame = readText(this::class, "example.txt")
            .split("\n\n")
            .map { Monkey.of(it) }
            .toMutableList()
            .let { MonkeyGame(it) }

        monkeyGame.play(20)
        monkeyGame.monkeyBusiness shouldBe 10_605L
    }

    @Test
    fun `Part 1`() {
        val monkeyGame = readText(this::class, "day11.txt")
            .split("\n\n")
            .map { Monkey.of(it) }
            .toMutableList()
            .let { MonkeyGame(it) }

        monkeyGame.play(20)
        monkeyGame.monkeyBusiness shouldBe 66_802L
    }

    @Test
    fun `Part 2 example`() {
        val monkeys = readText(this::class, "example.txt")
            .split("\n\n")
            .map { Monkey.of(it) }
            .toMutableList()

        val lcm = monkeys.map { it.test.divisibleBy }.toList().lcm()
        val monkeyGame = MonkeyGame(monkeys) { it % lcm.toLong() }

        monkeyGame.play(20)
        monkeyGame.inspectedCounts.first { it.first == 0 }.second shouldBe 99L
        monkeyGame.inspectedCounts.first { it.first == 1 }.second shouldBe 97L
        monkeyGame.inspectedCounts.first { it.first == 2 }.second shouldBe 8L
        monkeyGame.inspectedCounts.first { it.first == 3 }.second shouldBe 103L

        monkeyGame.play(1000 - 20)
        monkeyGame.inspectedCounts.first { it.first == 0 }.second shouldBe 5204
        monkeyGame.inspectedCounts.first { it.first == 1 }.second shouldBe 4792
        monkeyGame.inspectedCounts.first { it.first == 2 }.second shouldBe 199
        monkeyGame.inspectedCounts.first { it.first == 3 }.second shouldBe 5192
    }

    @Test
    fun `Part 2 example 2`() {
        val monkeys = readText(this::class, "example.txt")
            .split("\n\n")
            .map { Monkey.of(it) }
            .toMutableList()

        val lcm = monkeys.map { it.test.divisibleBy }.toList().lcm()
        val monkeyGame = MonkeyGame(monkeys) { it % lcm.toLong() }

        monkeyGame.play(10_000)
        monkeyGame.monkeyBusiness shouldBe 2_713_310_158L
    }

    @Test
    fun `Part 2 full`() {
        val monkeys = readText(this::class, "day11.txt")
            .split("\n\n")
            .map { Monkey.of(it) }
            .toMutableList()

        val lcm = monkeys.map { it.test.divisibleBy }.toList().lcm()
        val monkeyGame = MonkeyGame(monkeys) { it % lcm.toLong() }

        monkeyGame.play(10_000)
        println(monkeyGame.monkeyBusiness)
    }
}
