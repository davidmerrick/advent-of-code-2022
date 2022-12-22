package io.github.davidmerrick.aoc2022.day20

import io.github.davidmerrick.aoc.util.TestUtil
import io.kotlintest.matchers.collections.shouldContainInOrder
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

class Day20Test {

    @Test
    fun `Part 1 example`() {
        val numbers = parseInput("example.txt")
        numbers.mix()

        // I think there's a mistake in his example where the 2
        // doesn't move far enough
        numbers.map { it.value } shouldContainInOrder listOf(-2, 1, 2, -3, 4, 0, 3)
    }

    @Test
    fun `Part 1 full`() {
        val numbers = parseInput("day20.txt")
        numbers.mix()
        numbers.groveCoordinates().sum()
            .let { println(it) }
    }

    @Test
    fun `Part 2 full`() {
        val numbers = parseInput("day20.txt")
        numbers.mixPart2()

        numbers.groveCoordinates()
            .sum() shouldBe 5_382_459_262_696
    }

    @Test
    fun `Get grove coordinates`() {
        listOf(1, 2, -3, 4, 0, 3, -2)
            .toMappedInts()
            .groveCoordinates() shouldContainInOrder listOf(4, -3, 2)
    }

    private fun parseInput(fileName: String): MutableList<IndexedNumber> {
        return TestUtil.readLines(this::class, fileName)
            .map { it.toInt() }
            .toMappedInts()
    }
}
