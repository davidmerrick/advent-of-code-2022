package io.github.davidmerrick.aoc2022.day20

import io.github.davidmerrick.aoc.util.TestUtil
import io.kotlintest.matchers.collections.shouldContainInOrder
import org.junit.jupiter.api.Test

class Day20Test {

    @Test
    fun `Part 1 example`() {
        val numbers = parseInput("example.txt")
        numbers.mix()
        numbers.map { it.value } shouldContainInOrder listOf(1, 2, -3, 4, 0, 3, -2)
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
