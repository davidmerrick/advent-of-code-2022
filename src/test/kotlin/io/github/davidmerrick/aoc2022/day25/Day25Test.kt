package io.github.davidmerrick.aoc2022.day25

import io.github.davidmerrick.aoc.util.TestUtil.readLines
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

class Day25Test {

    @Test
    fun `Part 1 example`() {
        val snafuNumbers = readLines(this::class, "example.txt")
            .map { SnafuNumber(it) }

        snafuNumbers.sumOf { it.toInt() } shouldBe 4890
    }
}
