package io.github.davidmerrick.aoc2022.day14

import io.github.davidmerrick.aoc.util.TestUtil
import org.junit.jupiter.api.Test

class Day14Test {

    @Test
    fun `Part 1 full`() {
        TestUtil.readLines(this::class, "day14.txt")
            .let { SandGrid.of(it) }
            .pourSand()
            .let { println(it) }
    }
}
