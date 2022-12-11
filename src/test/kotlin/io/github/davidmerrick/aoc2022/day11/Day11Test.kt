package io.github.davidmerrick.aoc2022.day11

import io.github.davidmerrick.aoc.util.TestUtil.readText
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

class Day11Test {

    @Test
    fun `Part 1 example`() {
        val monkeys = readText(this::class, "example.txt")
            .split("\n\n")
            .map { Monkey.of(it) }
        monkeys.size shouldBe 4
    }
}
