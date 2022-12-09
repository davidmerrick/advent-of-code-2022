package io.github.davidmerrick.aoc2022.day2

import io.github.davidmerrick.aoc.util.TestUtil.readLines
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

class Day2Test {

    @Test
    fun `Part 1 example`() {
        """
            A Y
            B X
            C Z
        """.trimIndent()
            .lines()
            .map { parseLine(it) }
            .sumOf { it.yourScore } shouldBe 15
    }

    @Test
    fun `Part 1 full`() {
        readLines(this::class, "day2.txt")
            .map { parseLine(it) }
            .sumOf { it.yourScore }
            .let { println(it) }
    }

    @Test
    fun `Part 2 example`() {
        """
            A Y
            B X
            C Z
        """.trimIndent()
            .lines()
            .map { parseLinePart2(it) }
            .sumOf { it.yourScore } shouldBe 12
    }

    @Test
    fun `Part 2 full`() {
        readLines(this::class, "day2.txt")
            .map { parseLinePart2(it) }
            .sumOf { it.yourScore }
            .let { println(it) }
    }

    private fun parseLine(line: String): RockPaperScissorsGame {
        val split = line.trim().split(" ")
        return RockPaperScissorsGame.of(
            split[0].first(),
            split[1].first()
        )
    }

    private fun parseLinePart2(line: String): RockPaperScissorsGame {
        val split = line.trim().split(" ")
        return RockPaperScissorsGame(
            ShapeResolver.resolve(split[0].first()),
            ShapeResolver.resolveByOutcome(split[0].first(), split[1].first())
        )
    }
}
