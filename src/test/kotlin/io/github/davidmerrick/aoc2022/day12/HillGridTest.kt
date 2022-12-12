package io.github.davidmerrick.aoc2022.day12

import io.github.davidmerrick.aoc.util.TestUtil.readLines
import org.junit.jupiter.api.Test

internal class HillGridTest {

    @Test
    fun `Parse grid`(){
        val hillGrid = readLines(this::class, "example.txt")
            .let { HillGrid.of(it) }

        println(hillGrid)
    }
}
