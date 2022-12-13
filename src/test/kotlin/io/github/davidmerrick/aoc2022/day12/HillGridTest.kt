package io.github.davidmerrick.aoc2022.day12

import io.github.davidmerrick.aoc.util.TestUtil.readLines
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

internal class HillGridTest {

    @Test
    fun `Part 1 example`(){
        val hillGrid = readLines(this::class, "example.txt")
            .let { HillGrid.of(it) }

        hillGrid.shortestPath({ it == 'S' }, { it == 'E' }) shouldBe 31
    }
}
