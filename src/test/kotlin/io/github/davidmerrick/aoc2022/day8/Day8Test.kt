package io.github.davidmerrick.aoc2022.day8

import io.github.davidmerrick.aoc.util.TestUtil
import org.junit.jupiter.api.Test

class Day8Test {

    @Test
    fun `Part 1`() {
        TestUtil.readLines(this::class, "day8.txt")
            .let { TreeGrid.of(it) }
            .visibleCount
            .let { println(it) }
    }

    @Test
    fun `Part 2`() {
        TestUtil.readLines(this::class, "day8.txt")
            .let { TreeGrid.of(it) }
            .maxScenicScore()
            .let { println(it) }
    }
}
