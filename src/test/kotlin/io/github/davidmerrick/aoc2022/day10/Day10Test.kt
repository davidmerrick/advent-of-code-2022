package io.github.davidmerrick.aoc2022.day10

import io.github.davidmerrick.aoc.util.TestUtil
import org.junit.jupiter.api.Test

class Day10Test {

    @Test
    fun `Part 1 full`() {
        TestUtil.readLines(this::class, "day10.txt")
            .let { CathodeCpu.execute(it) }
            .let { println(it) }
    }

    @Test
    fun `Part 2 full`() {
        TestUtil.readLines(this::class, "day10.txt")
            .let { CathodeCpu.draw(it) }
            .let { println(it) }
    }
}
