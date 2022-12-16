package io.github.davidmerrick.aoc2022.day15

import io.github.davidmerrick.aoc.util.TestUtil.readLines
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

class Day15Test {

    @Test
    fun `Part 1 example`() {
        readLines(this::class, "example.txt")
            .map { SensorContext.of(it) }
            .let { SensorGrid(it) }
            .countNonBeaconPositions(10) shouldBe 26
    }

    @Test
    fun `Part 1`() {
        // Todo: 4668614 is too low
        readLines(this::class, "day15.txt")
            .map { SensorContext.of(it) }
            .let { SensorGrid(it) }
            .countNonBeaconPositions(2_000_000)
            .let { println(it) }
    }
}
