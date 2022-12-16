package io.github.davidmerrick.aoc2022.day15

import io.github.davidmerrick.aoc.coordinates.Pos
import io.github.davidmerrick.aoc.coordinates.Range
import io.github.davidmerrick.aoc.coordinates.manhattanDistance
import io.github.davidmerrick.aoc2022.day15.DeviceType.SENSOR

class SensorGrid(input: List<SensorContext>) {

    private val beaconDistances: Map<Pos, Int>
    private val devices: Set<Device>

    init {
        beaconDistances = input.associate { it.sensor.position to it.distanceToBeacon }
        devices = input.flatMap { listOf(it.sensor, it.closestBeacon) }.toSet()
    }

    /**
     * Computes min row position that needs to be checked for beacons
     */
    private fun computeMinRowPosition(row: Int): Pos {
        return devices.filter { it.type == SENSOR }
            .minOf { it.position.x - beaconDistances[it.position]!! }
            .let { Pos(it, row) }
    }

    /**
     * Computes max row position that needs to be checked for beacons
     */
    private fun computeMaxRowPosition(row: Int): Pos {
        return devices.filter { it.type == SENSOR }
            .maxOf { it.position.x + beaconDistances[it.position]!! }
            .let { Pos(it, row) }
    }

    fun tuningFrequency(minXY: Int, maxXY: Int): Long {
        return Range(Pos(minXY, minXY), Pos(maxXY, maxXY))
            .boxify()
            .filterNot { it in devices.map(Device::position) }
            .first { !cannotHaveBeacon(it) }
            .let { it.x.toLong() * 4_000_000L + it.y.toLong() }
    }

    fun countNonBeaconPositions(row: Int): Int {
        return Range(computeMinRowPosition(row), computeMaxRowPosition(row))
            .boxify(minY = row, maxY = row)
            .filterNot { it in devices.map(Device::position) }
            .filter { it.y == row }
            .count { cannotHaveBeacon(it) }
    }

    private fun cannotHaveBeacon(pos: Pos): Boolean {
        return beaconDistances.any { it.key.manhattanDistance(pos) <= it.value }
    }
}
