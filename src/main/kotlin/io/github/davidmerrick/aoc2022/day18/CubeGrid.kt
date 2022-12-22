package io.github.davidmerrick.aoc2022.day18

import io.github.davidmerrick.aoc.coordinates.Pos3d

class CubeGrid(private val cubes: Set<Pos3d>) {

    fun surfaceArea(): Int {
        return (cubes.size * 6) - cubes.sumOf { it.neighbors().count { neighbor -> neighbor in cubes } }
    }

    /**
     * Does a BFS of the cubes to find ones reachable from the outside
     */
    fun exteriorSurfaceArea(): Int {
        val xRange = getRange { it.x }
        val yRange = getRange { it.y }
        val zRange = getRange { it.z }

        val visited = mutableSetOf<Pos3d>()

        val start = Pos3d(xRange.min(), yRange.min(), zRange.min())
        val queue = ArrayDeque<Pos3d>().apply { add(start) }
        visited.add(start)

        var surfaceArea = 0
        while (queue.isNotEmpty()) {
            val curPos = queue.removeFirst()
            val neighbors = curPos.neighbors()
                .filterNot { it in visited }
                .filter { it.x in xRange && it.y in yRange && it.z in zRange }

            surfaceArea += neighbors.count { !isAir(it) }

            // Only add the "air" cubes to the visited list and the queue
            neighbors.filter { isAir(it) }
                .forEach {
                    visited.add(it)
                    queue.addLast(it)
                }
        }
        return surfaceArea
    }

    private fun isAir(pos: Pos3d) = pos !in cubes
    private fun getRange(dimension: (Pos3d) -> Int): IntRange {
        return (cubes.minOf { dimension(it) } - 1)..(cubes.maxOf { dimension(it) } + 1)
    }

    companion object {
        fun of(lines: List<String>): CubeGrid {
            return lines.map { Pos3d.of(it) }
                .toSet()
                .let { CubeGrid(it) }
        }
    }
}
