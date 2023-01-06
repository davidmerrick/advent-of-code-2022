package io.github.davidmerrick.aoc2022.day16

import io.github.davidmerrick.aoc.util.TestUtil.readLines
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

class Day16Test {

    @Test
    fun `Part 1 full`() {
        val maze = readLines(this::class, "day16.txt")
            .let { ValveMaze.of(it) }

        maze.searchPaths("AA", 30) shouldBe 2253
    }
}
