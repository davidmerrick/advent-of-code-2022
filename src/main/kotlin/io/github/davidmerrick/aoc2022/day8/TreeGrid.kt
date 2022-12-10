package io.github.davidmerrick.aoc2022.day8

import com.google.common.collect.HashBasedTable
import io.github.davidmerrick.aoc.guava.asSequence
import io.github.davidmerrick.aoc.guava.parseTable
import io.github.davidmerrick.aoc.util.mapToInts

class TreeGrid(private val table: HashBasedTable<Int, Int, Int>) {

    val visibleCount: Int by lazy {
        table.asSequence().count { isVisible(it.row, it.column) }
    }

    fun isVisible(row: Int, column: Int): Boolean {
        if (isEdge(row, column)) return true

        TODO()
    }

    private fun isEdge(row: Int, column: Int): Boolean {
        return row == 0 || column == 0 || row == table.rowMap().size - 1 || column == table.columnMap().size - 1
    }

    companion object {
        fun of(lines: List<String>) = TreeGrid(parseTable(lines.mapToInts()))
    }
}
