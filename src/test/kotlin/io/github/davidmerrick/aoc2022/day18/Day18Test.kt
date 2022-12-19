package io.github.davidmerrick.aoc2022.day18

import io.github.davidmerrick.aoc.coordinates.Pos3d
import io.github.davidmerrick.aoc.util.TestUtil
import org.junit.jupiter.api.Test

class Day18Test {

    @Test
    fun `Part 1`(){
        TestUtil.readLines(this::class, "day18.txt")
            .map { Pos3d.of(it) }
            .let { CubeGrid(it) }
            .surfaceArea()
            .let { println(it) }
    }
}
