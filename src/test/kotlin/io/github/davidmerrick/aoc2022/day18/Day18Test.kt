package io.github.davidmerrick.aoc2022.day18

import io.github.davidmerrick.aoc.util.TestUtil
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

class Day18Test {

    @Test
    fun `Part 1`() {
        TestUtil.readLines(this::class, "day18.txt")
            .let { CubeGrid.of(it) }
            .surfaceArea() shouldBe 3454
    }

    @Test
    fun `Part 2`() {
        // Todo: 2013 is too low, 2222 is too high
        // Need to take a different approach--can't assume this is a closed cube with a single air pocket
        // Need to do a DFS or BFS to determine if if a position is reachable from the outside

        TestUtil.readLines(this::class, "day18.txt")
            .let { CubeGrid.of(it) }
            .externalSurfaceArea()
            .let { println(it) }
    }
}
