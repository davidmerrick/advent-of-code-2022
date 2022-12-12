package io.github.davidmerrick.aoc2022.day12

import com.google.common.collect.HashBasedTable
import io.github.davidmerrick.aoc.guava.parseTable

class HillGrid(private val table: HashBasedTable<Int, Int, Char>) {



    companion object {
        fun of(lines: List<String>): HillGrid {
            return HillGrid(parseTable(lines.map { it.toList() }))
        }
    }
}
