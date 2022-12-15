package io.github.davidmerrick.aoc2022.day14

import io.github.davidmerrick.aoc.util.TestUtil.readLines
import org.junit.jupiter.api.Test

internal class SandGridTest {



    @Test
    fun `Parse grid`(){
        val sandGrid = readLines(this::class, "example.txt")
            .let { SandGrid.of(it) }
        print(sandGrid.print())
    }
}