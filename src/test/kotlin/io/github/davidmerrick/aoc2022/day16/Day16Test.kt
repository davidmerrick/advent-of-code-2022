package io.github.davidmerrick.aoc2022.day16

import io.github.davidmerrick.aoc.util.TestUtil.readLines
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

class Day16Test {

    @Test
    fun `Part 1 example`() {
        readLines(this::class, "example.txt")
            .let { ValveMaze.of(it) }
            .optimalRoute() shouldBe 1651
    }
}
