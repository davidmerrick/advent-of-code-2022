package io.github.davidmerrick.aoc2022.day11

import io.github.davidmerrick.aoc.util.TestUtil.readText
import io.kotlintest.matchers.collections.shouldContainAll
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
        monkeyGame.monkeyBusiness shouldBe 10_605UL
    }

    @Test
    fun `Part 1`() {
        val monkeyGame = readText(this::class, "day11.txt")
            .split("\n\n")
            .map { Monkey.of(it) }
            .toMutableList()
            .let { MonkeyGame(it) }

        monkeyGame.play(20)
        monkeyGame.monkeyBusiness shouldBe 66_802UL
    }

    @Test
    fun `Part 2 example`() {
        val monkeyGame = readText(this::class, "example.txt")
            .split("\n\n")
            .map { Monkey.of(it) }
            .toMutableList()
            .let { MonkeyGame(it) { worry -> worry } }

        monkeyGame.play(20)
        monkeyGame.inspectedCounts.first { it.first == 0 }.second shouldBe 99UL
        monkeyGame.inspectedCounts.first { it.first == 1 }.second shouldBe 97UL
        monkeyGame.inspectedCounts.first { it.first == 2 }.second shouldBe 8UL
        monkeyGame.inspectedCounts.first { it.first == 3 }.second shouldBe 103UL

        monkeyGame.play(1000 - 20)
        monkeyGame.inspectedCounts.first { it.first == 0 }.second shouldBe 5204
        monkeyGame.inspectedCounts.first { it.first == 1 }.second shouldBe 4792
        monkeyGame.inspectedCounts.first { it.first == 2 }.second shouldBe 199
        monkeyGame.inspectedCounts.first { it.first == 3 }.second shouldBe 5192
    }
}
