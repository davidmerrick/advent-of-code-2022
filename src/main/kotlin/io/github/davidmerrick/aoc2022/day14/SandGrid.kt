package io.github.davidmerrick.aoc2022.day14

import com.google.common.collect.HashBasedTable
import io.github.davidmerrick.aoc.coordinates.Range
import io.github.davidmerrick.aoc.guava.put

class SandGrid(private val table: HashBasedTable<Int, Int, Boolean>) {


    companion object {
        fun of(lines: List<String>): SandGrid {
            val table = HashBasedTable.create<Int, Int, Boolean>()
            lines.flatMap { parseRanges(it) }
                .flatMap { it.positions }
                .forEach { table.put(it, true) }
            return SandGrid(table)
        }

        private fun parseRanges(line: String): List<Range> {
            TODO()
        }
    }
}
