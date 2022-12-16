package io.github.davidmerrick.aoc2022.day14

import io.github.davidmerrick.aoc.util.TestUtil.readLines
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

internal class SandGridTest {


    @Test
    fun `Part 1 example`() {
        readLines(this::class, "example.txt")
            .let { SandGrid.of(it) }
            .pourSand() shouldBe 24
    }
}