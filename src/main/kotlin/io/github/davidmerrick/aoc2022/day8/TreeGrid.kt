package io.github.davidmerrick.aoc2022.day8

import com.google.common.collect.HashBasedTable
import io.github.davidmerrick.aoc.collections.toIntRows
import io.github.davidmerrick.aoc.guava.asSequence
import io.github.davidmerrick.aoc.guava.columnRange
import io.github.davidmerrick.aoc.guava.parseTable
import io.github.davidmerrick.aoc.guava.rowRange
import io.github.davidmerrick.aoc.util.product

class TreeGrid(private val table: HashBasedTable<Int, Int, Int>) {

    val visibleCount: Int by lazy {
        table.asSequence().count { isVisible(it.row, it.column) }
    }

    fun maxScenicScore() = table.asSequence().maxOf { scenicScore(it.row, it.column) }

    fun isVisible(row: Int, column: Int): Boolean {
        if (isEdge(row, column)) return true

        val value = table.get(row, column)!!

        // Check above, below, left, right
        return sequenceOf(
            table.rowRange(column, row + 1, table.rowMap().size),
            table.rowRange(column, 0, row),
            table.columnRange(row, column + 1, table.columnMap().size),
            table.columnRange(row, 0, column)
        ).any { range -> range.none { it >= value } }
    }

    fun scenicScore(row: Int, column: Int): Int {
        val value = table.get(row, column)!!
        return sequenceOf(
            table.rowRange(column, row + 1, table.rowMap().size),
            table.rowRange(column, 0, row).reversed(),
            table.columnRange(row, column + 1, table.columnMap().size),
            table.columnRange(row, 0, column).reversed()
        )
            .map { visibility(value, it) }
            .product()
    }

    private fun visibility(value: Int, trees: List<Int>): Int {
        var score = 0
        for (tree in trees) {
            if(tree >= value){
                score++
                break
            }

            score++
        }
        return score
    }

    private fun isEdge(row: Int, column: Int): Boolean {
        return row == 0 || column == 0 || row == table.rowMap().size - 1 || column == table.columnMap().size - 1
    }

    companion object {
        fun of(lines: List<String>) = TreeGrid(parseTable(lines.toIntRows()))
    }
}
