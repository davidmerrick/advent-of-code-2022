package io.github.davidmerrick.aoc2022.day14

import io.github.davidmerrick.aoc.util.TestUtil
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

class Day14Test {

    @Test
    fun `Part 1 full`() {
        TestUtil.readLines(this::class, "day14.txt")
            .let { SandGrid.of(it) }
            .pourSand()
            .let { println(it) }
    }

    @Test
    fun `Part 2 example`() {
        // Todo: Need to allow sand to flow into empty cells in table

        val sandGrid = TestUtil.readLines(this::class, "example.txt")
            .let { SandGridPart2.of(it) }

        sandGrid.pourSand() shouldBe 93
        print(sandGrid.print())
    }
}
