package io.github.davidmerrick.aoc2022.day16

import io.github.davidmerrick.aoc.util.TestUtil.readLines
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

internal class ValveMazeTest {


    @Test
    fun `Compute shortest paths`() {
        val paths = readLines(this::class, "example.txt")
            .let { ValveMaze.of(it) }
            .shortestPaths

        paths.rowKeySet().size shouldBe 6
    }

    @Test
    fun `Part 1 example`() {
        val maze = readLines(this::class, "example.txt")
            .let { ValveMaze.of(it) }

        val result = maze.searchPaths("AA", 30)
        result shouldBe 1651
    }
}
