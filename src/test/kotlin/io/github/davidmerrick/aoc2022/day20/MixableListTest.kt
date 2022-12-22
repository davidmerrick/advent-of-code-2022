package io.github.davidmerrick.aoc2022.day20

import io.github.davidmerrick.aoc.util.TestUtil.readLines
import io.kotlintest.matchers.collections.shouldContainInOrder
import org.junit.jupiter.api.Test

internal class MixableListTest {

    @Test
    fun `Part 1 test`() {
        readLines(this::class, "example.txt")
            .map { it.toInt() }
            .let { MixableList(it) }
            .mix() shouldContainInOrder listOf(1, 2, -3, 4, 0, 3, -2)
    }
}
