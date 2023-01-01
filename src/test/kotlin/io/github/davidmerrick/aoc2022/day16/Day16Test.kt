package io.github.davidmerrick.aoc2022.day16

import io.github.davidmerrick.aoc.util.TestUtil.readLines
import org.junit.jupiter.api.Test

class Day16Test {

    @Test
    fun `Part 1 full`() {
        val maze = readLines(this::class, "day16.txt")
            .let { ValveMaze.of(it) }

        println(maze.searchPaths("AA", 30))
    }
}
