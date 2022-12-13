package io.github.davidmerrick.aoc2022.day12

import io.github.davidmerrick.aoc.util.TestUtil
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

class Day12Test {

    @Test
    fun `Part 1`() {
        val hillGrid = TestUtil.readLines(this::class, "day12.txt")
            .let { HillGrid.of(it) }

        hillGrid.shortestPath({ it == 'S' }, { it == 'E' })
            .let { println(it) }
    }

    @Test
    fun `Part 2 example`() {
        val hillGrid = TestUtil.readLines(this::class, "example.txt")
            .let { HillGrid.of(it) }

        hillGrid.shortestPath(
            { it == 'E' },
            { it == 'a' },
            { node, neighbor -> neighbor.elevation() >= node.elevation() - 1 }
        ) shouldBe 29
    }

    @Test
    fun `Part 2`() {
        val hillGrid = TestUtil.readLines(this::class, "day12.txt")
            .let { HillGrid.of(it) }

        hillGrid.shortestPath(
            { it == 'E' },
            { it == 'a' },
            { node, neighbor -> neighbor.elevation() >= node.elevation() - 1 }
        ).let { println(it) }
    }
}
