package io.github.davidmerrick.aoc2022.day15

import io.github.davidmerrick.aoc.coordinates.IntPos
import io.github.davidmerrick.aoc.coordinates.Pos
import io.github.davidmerrick.aoc.coordinates.Range

class SensorGrid(private val sensors: Set<Sensor>) {

    /**
     * Computes min row position that needs to be checked for beacons
     */
    private fun computeMinRowPosition(row: Int): IntPos {
        return sensors
            .minOf { it.position.x - it.beaconDistance }
            .let { IntPos(it, row) }
    }

    /**
     * Computes max row position that needs to be checked for beacons
     */
    private fun computeMaxRowPosition(row: Int): IntPos {
        return sensors
            .maxOf { it.position.x + it.beaconDistance }
            .let { IntPos(it, row) }
    }

    fun tuningFrequency(caveSize: Int): Long {
        val cave = (0..caveSize)
        return sensors.flatMap { sensor ->
            val up = IntPos(sensor.position.x, sensor.position.y + sensor.beaconDistance + 1)
            val down = IntPos(sensor.position.x, sensor.position.y - sensor.beaconDistance - 1)
            val left = IntPos(sensor.position.x - sensor.beaconDistance - 1, sensor.position.y)
            val right = IntPos(sensor.position.x + sensor.beaconDistance + 1, sensor.position.y)

            (up.lineTo(right) + right.lineTo(down) + down.lineTo(left) + left.lineTo(up))
                .toSet()
                .filter { it.x in cave && it.y in cave }
        }.first { sensors.none { sensor -> sensor.isInRange(it) } }
            .tuningFrequency()
    }

    fun countNonBeaconPositions(row: Int): Int {
        return Range(computeMinRowPosition(row), computeMaxRowPosition(row))
            .boxify(minY = row, maxY = row)
            .filterNot { it in sensors.map(Sensor::position) }
            .filter { it.y == row }
            .count { pos -> sensors.none { it.isInRange(pos) } }
    }
}

private fun IntPos.tuningFrequency() = (x * 40_000_00L) + y
