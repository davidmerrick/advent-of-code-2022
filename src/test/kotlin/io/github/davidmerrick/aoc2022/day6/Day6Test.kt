package io.github.davidmerrick.aoc2022.day6

import io.github.davidmerrick.aoc.util.TestUtil.readText
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

private const val START_OF_PACKET_SIZE = 4
private const val START_OF_MESSAGE_SIZE = 14

class Day6Test {

    @Test
    fun `Part 1 example`() {
        "bvwbjplbgvbhsrlpgdmjqwftvncz"
            .withIndex()
            .windowed(START_OF_PACKET_SIZE)
            .first { allDifferent(it) }
            .let { it.first().index + START_OF_PACKET_SIZE } shouldBe 5
    }

    @Test
    fun `Part 1 full`() {
        readText(this::class, "day6.txt")
            .withIndex()
            .windowed(START_OF_PACKET_SIZE)
            .first { allDifferent(it) }
            .let { it.first().index + START_OF_PACKET_SIZE }
            .let { println(it) }
    }

    @Test
    fun `Part 2 example`() {
        "bvwbjplbgvbhsrlpgdmjqwftvncz"
            .withIndex()
            .windowed(START_OF_MESSAGE_SIZE)
            .first { allDifferent(it) }
            .let { it.first().index + START_OF_MESSAGE_SIZE } shouldBe 23
    }

    @Test
    fun `Part 2 full`() {
        readText(this::class, "day6.txt")
            .withIndex()
            .windowed(START_OF_MESSAGE_SIZE)
            .first { allDifferent(it) }
            .let { it.first().index + START_OF_MESSAGE_SIZE }
            .let { println(it) }
    }

    private fun allDifferent(chars: List<IndexedValue<Char>>): Boolean {
        return chars.map { it.value }.toSet().size == chars.size
    }
}
