package io.github.davidmerrick.aoc2022.day16

import io.github.davidmerrick.aoc.util.TestUtil.readLines
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

internal class ValveMazeTest {


    @Test
    fun `Compute shortest paths`(){
        val maze = readLines(this::class, "example.txt")
            .let { ValveMaze.of(it) }

        val paths = maze.shortestPaths()
        paths.size shouldBe 6
    }
}
