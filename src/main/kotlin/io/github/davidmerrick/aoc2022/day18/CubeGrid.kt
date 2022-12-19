package io.github.davidmerrick.aoc2022.day18

import io.github.davidmerrick.aoc.coordinates.Pos3d

class CubeGrid(private val cubes: List<Pos3d>) {

    private val intersections = cubes
        .flatMap { it.flatten() }
        .groupingBy { it }
        .eachCount()

    fun surfaceArea(): Int {
        val intersectionCount = intersections.values.filter { it > 1 }.sumOf { it }
        return (cubes.size * 6) - intersectionCount
    }
}

/**
 * Returns all 2d coordinate pairs
 */
fun Pos3d.flatten(): List<List<Int?>> {
    return listOf(
        listOf(x, null, z),
        listOf(x, y, null),
        listOf(null, y, z)
    )
}
