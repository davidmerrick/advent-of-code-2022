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
        readLines(this::class, "day15.txt")
            .map { SensorContext.of(it) }
            .let { SensorGrid(it) }
            .countNonBeaconPositions(2_000_000) shouldBe 5_878_678
    }

    @Test
    fun `Part 2 example`() {
        readLines(this::class, "example.txt")
            .map { SensorContext.of(it) }
            .let { SensorGrid(it) }
            .tuningFrequency(0, 20) shouldBe 56_000_011L
    }

    @Test
    fun `Part 2`() {
        readLines(this::class, "day15.txt")
            .map { SensorContext.of(it) }
            .let { SensorGrid(it) }
            .tuningFrequency(0, 4_000_000)
            .let { println(it) }
    }
}
