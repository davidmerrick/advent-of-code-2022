package io.github.davidmerrick.aoc2022.day16

import io.github.davidmerrick.aoc.util.TestUtil.readLines
import org.junit.jupiter.api.Test

internal class ValveMazeTest {


    @Test
    fun `Compute shortest paths`(){
        val maze = readLines(this::class, "example.txt")
            .let { ValveMaze.of(it) }

        maze.shortestPaths()
        println("foo")
    }
}
