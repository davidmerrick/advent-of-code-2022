package io.github.davidmerrick.aoc2022.day12

import io.github.davidmerrick.aoc.util.TestUtil
import org.junit.jupiter.api.Test

class Day12Test {

    @Test
    fun `Part 1 full`(){
        val hillGrid = TestUtil.readLines(this::class, "day12.txt")
            .let { HillGrid.of(it) }

        hillGrid.shortestPath({ it == 'S' }, { it == 'E' })
            .let { println(it) }
    }
}
