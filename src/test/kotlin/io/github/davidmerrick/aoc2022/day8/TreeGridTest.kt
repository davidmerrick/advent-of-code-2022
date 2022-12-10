package io.github.davidmerrick.aoc2022.day8

import io.github.davidmerrick.aoc.util.TestUtil.readLines
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

internal class TreeGridTest {


    @Test
    fun `Edges should be visible`() {
        val grid = readLines(this::class, "example.txt")
            .let { TreeGrid.of(it) }

        grid.isVisible(0, 0) shouldBe true
        grid.isVisible(4, 0) shouldBe true
        grid.isVisible(0, 4) shouldBe true
        grid.isVisible(4, 4) shouldBe true
    }

    @Test
    fun `Visibility count`() {
        readLines(this::class, "example.txt")
            .let { TreeGrid.of(it) }
            .visibleCount shouldBe 21
    }

    @Test
    fun `Scenic score`() {
        readLines(this::class, "example.txt")
            .let { TreeGrid.of(it) }
            .scenicScore(1, 2) shouldBe 4
    }

    @Test
    fun `Scenic score 2`() {
        readLines(this::class, "example.txt")
            .let { TreeGrid.of(it) }
            .scenicScore(3, 2) shouldBe 8
    }

    @Test
    fun `Max scenic score`() {
        readLines(this::class, "example.txt")
            .let { TreeGrid.of(it) }
            .maxScenicScore() shouldBe 8
    }
}
