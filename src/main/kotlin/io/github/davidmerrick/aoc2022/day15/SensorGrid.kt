package io.github.davidmerrick.aoc2022.day15

import io.github.davidmerrick.aoc.coordinates.Pos
import io.github.davidmerrick.aoc.coordinates.Range

class SensorGrid(private val sensors: Set<Sensor>) {

    /**
     * Computes min row position that needs to be checked for beacons
     */
    private fun computeMinRowPosition(row: Int): Pos {
        return sensors
            .minOf { it.position.x - it.beaconDistance }
            .let { Pos(it, row) }
    }

    /**
     * Computes max row position that needs to be checked for beacons
     */
    private fun computeMaxRowPosition(row: Int): Pos {
        return sensors
            .maxOf { it.position.x + it.beaconDistance }
            .let { Pos(it, row) }
    }

//    fun tuningFrequency(caveSize: Int): Long {
//        // Todo: Start with the sensors
//        // Use them plus distance, constrained by the cavesize, to generate a set of possible ranges
//
//        val cave = (0..caveSize)
//        return sensors().firstNotNullOf { sensor ->
//            val up = Pos(sensor.position.x, sensor.position.y - sensor - 1)
//            val down = Pos(sensor.position.x, sensor.position.y + sensor.distance + 1)
//            val left = Pos(sensor.position.x - sensor.distance - 1, sensor.position.y)
//            val right = Pos(sensor.position.x + sensor.distance + 1, sensor.position.y)
//
//            (up.lineTo(right) + right.lineTo(down) + down.lineTo(left) + left.lineTo(up))
//                .filter { it.x in cave && it.y in cave }
//                .firstOrNull { candidate -> sensors.none { sensor -> sensor.isInRange(candidate) } }
//        }.tuningFrequency()
//
//    }

    fun countNonBeaconPositions(row: Int): Int {
        return Range(computeMinRowPosition(row), computeMaxRowPosition(row))
            .boxify(minY = row, maxY = row)
            .filterNot { it in sensors.map(Sensor::position) }
            .filter { it.y == row }
            .count { pos -> sensors.none { it.isInRange(pos) } }
    }
}

private fun Pos.tuningFrequency() = (x * 40_000_00L) + y
