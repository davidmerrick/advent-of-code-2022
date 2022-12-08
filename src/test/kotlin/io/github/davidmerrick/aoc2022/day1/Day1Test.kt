package io.github.davidmerrick.aoc2022.day1

import io.github.davidmerrick.aoc.util.TestUtil.readText
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

class Day1Test {
    @Test
    fun `Example for part 1`() {
        parseInput("example.txt")
            .maxOf { it.totalCalories } shouldBe 24_000
    }

    @Test
    fun `Example for part 2`() {
        parseInput("example.txt")
            .map { it.totalCalories }
            .sortedDescending()
            .take(3)
            .sum() shouldBe 45_000
    }

    @Test
    fun `Part 1`() {
        parseInput("day1.txt")
            .maxOf { it.totalCalories }
            .let { println(it) }
    }

    @Test
    fun `Part 2`() {
        parseInput("day1.txt")
            .map { it.totalCalories }
            .sortedDescending()
            .take(3)
            .sum()
            .let { println(it) }
    }

    private fun parseInput(fileName: String): List<Food> {
        return readText(this::class, fileName)
            .split("\n\n")
            .map { Food.parse(it) }
    }
}
