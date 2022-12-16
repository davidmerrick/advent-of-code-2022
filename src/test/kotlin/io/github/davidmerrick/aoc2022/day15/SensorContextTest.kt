package io.github.davidmerrick.aoc2022.day15

import io.github.davidmerrick.aoc.coordinates.Pos
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

internal class SensorContextTest {

    @Test
    fun `Parse context`(){
        val ctx = SensorContext.of(
            "Sensor at x=13, y=2: closest beacon is at x=15, y=3"
        )

        ctx.sensor shouldBe Pos(13, 2)
        ctx.closestBeacon shouldBe Pos(15, 3)
    }
}
