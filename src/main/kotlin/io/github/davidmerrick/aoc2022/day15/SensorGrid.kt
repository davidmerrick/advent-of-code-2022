package io.github.davidmerrick.aoc2022.day15

import io.github.davidmerrick.aoc.coordinates.Pos
import io.github.davidmerrick.aoc.coordinates.Range
import io.github.davidmerrick.aoc.coordinates.manhattanDistance

class SensorGrid(input: List<SensorContext>) {

    private val beaconDistances: Map<Pos, Int>
    private val positions: Set<Pos>

    init {
        beaconDistances = input.associate { it.sensor to it.distanceToBeacon }
        positions = input.flatMap { listOf(it.sensor, it.closestBeacon) }.toSet()
    }

    fun countNonBeaconPositions(row: Int): Int {
        return Range.of(positions)
            .positions(minY = row, maxY = row)
            .filterNot { it in positions }
            .filter { it.y == row }
            .count { pos ->
                beaconDistances.any { it.key.manhattanDistance(pos) <= it.value }
            }
    }
}
