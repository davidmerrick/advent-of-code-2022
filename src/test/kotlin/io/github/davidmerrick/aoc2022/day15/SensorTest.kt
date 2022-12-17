package io.github.davidmerrick.aoc2022.day15

import io.github.davidmerrick.aoc.coordinates.Pos
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

internal class SensorTest {

    @Test
    fun `Parse context`() {
        val ctx = Sensor.of(
            "Sensor at x=13, y=2: closest beacon is at x=15, y=3"
        )

        ctx.position shouldBe Pos(13, 2)
        ctx.beaconDistance shouldBe 3
    }
}
