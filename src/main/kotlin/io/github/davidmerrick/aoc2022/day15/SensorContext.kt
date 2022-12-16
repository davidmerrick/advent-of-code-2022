package io.github.davidmerrick.aoc2022.day15

import io.github.davidmerrick.aoc.coordinates.Pos
import io.github.davidmerrick.aoc.coordinates.manhattanDistance

data class SensorContext(
    val sensor: Pos,
    val closestBeacon: Pos
) {
    val distanceToBeacon = sensor.manhattanDistance(closestBeacon)

    companion object {
        fun of(line: String): SensorContext {
            return line.replace("Sensor at ", "")
                .replace(" closest beacon is at ", "")
                .split(":")
                .map { parsePos(it) }
                .let {
                    SensorContext(
                        sensor = it.first(),
                        closestBeacon = it.last()
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
