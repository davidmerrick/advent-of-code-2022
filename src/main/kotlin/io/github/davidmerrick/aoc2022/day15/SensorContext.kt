package io.github.davidmerrick.aoc2022.day15

import io.github.davidmerrick.aoc.coordinates.Pos
import io.github.davidmerrick.aoc.coordinates.manhattanDistance
import io.github.davidmerrick.aoc2022.day15.DeviceType.BEACON
import io.github.davidmerrick.aoc2022.day15.DeviceType.SENSOR

data class SensorContext(
    val sensor: Device,
    val closestBeacon: Device
) {
    val distanceToBeacon = sensor.position.manhattanDistance(closestBeacon.position)

    companion object {
        fun of(line: String): SensorContext {
            return line.replace("Sensor at ", "")
                .replace(" closest beacon is at ", "")
                .split(":")
                .map { parsePos(it) }
                .let {
                    SensorContext(
                        sensor = Device(it.first(), SENSOR),
                        closestBeacon = Device(it.last(), BEACON)
                    )
                }
        }

        /**
         * Given a string like:
         * "x=10, y=20",
         * parse it into a position
         */
        private fun parsePos(str: String): Pos {
            return str.split(", ")
                .map { it.split("=")[1].toInt() }
                .let { Pos(it.first(), it.last()) }
        }
    }
}
