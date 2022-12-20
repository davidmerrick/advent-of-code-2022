package io.github.davidmerrick.aoc2022.day18

import io.github.davidmerrick.aoc.coordinates.Pos3d

class CubeGrid(private val cubes: Set<Pos3d>) {

    fun surfaceArea(): Int {
        return (cubes.size * 6) - cubes.sumOf { it.neighbors().count { neighbor -> neighbor in cubes } }
    }

    val interiorCubes = cubes.flatMap { it.neighbors() }
        .toSet()
        .filterNot { it in cubes }
        .filter { isInternal(it) }

    private fun interiorSurfaceArea(): Int {
        // Count the intersections with internal cubes
        return cubes.sumOf { it.neighbors().count(interiorCubes::contains) }
    }

    private fun isInternal(cube: Pos3d): Boolean {
        return cube.x in (cubes.minOf { it.x } + 1 until cubes.maxOf { it.x }) &&
            cube.y in (cubes.minOf { it.y } + 1 until cubes.maxOf { it.y }) &&
            cube.z in (cubes.minOf { it.z } + 1 until cubes.maxOf { it.z })
    }

    fun externalSurfaceArea() = surfaceArea() - interiorSurfaceArea()

    companion object {
        fun of(lines: List<String>): CubeGrid {
            return lines.map { Pos3d.of(it) }
                .toSet()
                .let { CubeGrid(it) }
        }
    }
}
