package io.github.davidmerrick.aoc2022.day15

import io.github.davidmerrick.aoc.coordinates.Pos
import io.github.davidmerrick.aoc.coordinates.manhattanDistance

data class Sensor(
    val position: Pos,
    val beaconDistance: Int
) {
    fun isInRange(pos: Pos) = position.manhattanDistance(pos) <= beaconDistance

    companion object {
        fun of(line: String): Sensor {
            return line.replace("Sensor at ", "")
                .replace(" closest beacon is at ", "")
                .split(":")
                .map { parsePos(it) }
                .let {
                    Sensor(
                        it.first(),
                        beaconDistance = it.first().manhattanDistance(it.last())
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
