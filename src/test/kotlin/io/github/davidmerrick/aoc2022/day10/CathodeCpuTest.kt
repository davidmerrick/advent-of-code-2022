package io.github.davidmerrick.aoc2022.day10

import io.github.davidmerrick.aoc.util.TestUtil.readLines
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

internal class CathodeCpuTest {

    @Test
    fun `Part 1 example 2`() {
        readLines(this::class, "example2.txt")
            .let { CathodeCpu.execute(it) } shouldBe 13140
    }

    @Test
    fun `Part 2 example`() {
        readLines(this::class, "example2.txt")
            .let { CathodeCpu.draw(it) }
            .let { println(it) }
    }
}
