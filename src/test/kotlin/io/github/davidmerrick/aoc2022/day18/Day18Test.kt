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
        TestUtil.readLines(this::class, "day18.txt")
            .let { CubeGrid.of(it) }
            .exteriorSurfaceArea()
            .let { println(it) }
    }
}
