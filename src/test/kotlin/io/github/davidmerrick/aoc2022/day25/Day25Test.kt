package io.github.davidmerrick.aoc2022.day25

import io.github.davidmerrick.aoc.util.TestUtil.readLines
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

class Day25Test {

    @Test
    fun `Part 1 example`() {
        val snafuNumbers = readLines(this::class, "example.txt")
            .map { SnafuNumber(it) }

        val sum = snafuNumbers.sumOf { it.toDecimal() }
        sum shouldBe 4890
        SnafuNumber.of(sum).value shouldBe "2=-1=0"
    }

    @Test
    fun `Part 1 full`() {
        val snafuNumbers = readLines(this::class, "day25.txt")
            .map { SnafuNumber(it) }

        val sum = snafuNumbers.sumOf { it.toDecimal() }
        println(SnafuNumber.of(sum).value)
    }
}
