package io.github.davidmerrick.aoc2022.day15

import io.github.davidmerrick.aoc.coordinates.IntPos

data class Sensor(
    val position: IntPos,
    val beaconDistance: Int
) {
    fun isInRange(pos: IntPos) = position.manhattanDistance(pos) <= beaconDistance

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
        private fun parsePos(str: String): IntPos {
            return str.split(", ")
                .map { it.split("=")[1].toInt() }
                .let { IntPos(it.first(), it.last()) }
        }
    }
}
