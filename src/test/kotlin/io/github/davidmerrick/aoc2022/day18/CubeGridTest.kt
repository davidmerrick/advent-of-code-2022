package io.github.davidmerrick.aoc2022.day18

import io.github.davidmerrick.aoc.coordinates.Pos3d
import io.github.davidmerrick.aoc.util.TestUtil.readLines
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

internal class CubeGridTest {

    @Test
    fun `Surface area`() {
        val grid = listOf(
            Pos3d.of("1,1,1"),
            Pos3d.of("2,1,1")
        ).toSet()
            .let { CubeGrid(it) }
        grid.surfaceArea() shouldBe 10
    }

    @Test
    fun `Example part 1`() {
        readLines(this::class, "example.txt")
            .let { CubeGrid.of(it) }
            .surfaceArea() shouldBe 64
    }
}
