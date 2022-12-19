package io.github.davidmerrick.aoc2022.day18

import io.github.davidmerrick.aoc.coordinates.Pos3d

class CubeGrid(private val cubes: Set<Pos3d>) {

    fun surfaceArea(): Int {
        return (cubes.size * 6) - cubes.sumOf { it.neighbors().count { neighbor -> neighbor in cubes } }
    }

    companion object {
        fun of(lines: List<String>): CubeGrid {
            return lines.map { Pos3d.of(it) }
                .toSet()
                .let { CubeGrid(it) }
        }
    }
}
