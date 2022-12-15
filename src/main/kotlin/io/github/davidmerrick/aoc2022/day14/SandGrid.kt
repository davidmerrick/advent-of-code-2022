package io.github.davidmerrick.aoc2022.day14

import com.google.common.collect.HashBasedTable
import io.github.davidmerrick.aoc.coordinates.Pos
import io.github.davidmerrick.aoc.coordinates.Range
import io.github.davidmerrick.aoc.guava.fill
import io.github.davidmerrick.aoc.guava.print
import io.github.davidmerrick.aoc.guava.put
import io.github.davidmerrick.aoc2022.day14.SpaceType.AIR
import io.github.davidmerrick.aoc2022.day14.SpaceType.ROCK
import io.github.davidmerrick.aoc2022.day14.SpaceType.SAND


private val sandEntryPoint = Pos(500, 0)

class SandGrid(private val table: HashBasedTable<Int, Int, SpaceType>) {

    // Flips to false when sand falls off until infinity
    private var canPourSand = true
    fun print(): String {
        return table.print {
            when (it) {
                SAND -> "o"
                ROCK -> "#"
                else -> "."
            }
        }
    }

    fun pourSand(){
        TODO()

        // While sand is not at rest


    }

    companion object {
        fun of(lines: List<String>): SandGrid {
            val table = HashBasedTable.create<Int, Int, SpaceType>()
            val positions = lines.flatMap { parseRanges(it) }
                .flatMap { it.positions }
            table.fill(getFillRange(positions), AIR)
            positions
                .forEach { table.put(it, ROCK) }
            return SandGrid(table)
        }

        private fun parseRanges(line: String) = line.split(" -> ")
            .map { Pos.of(it) }
            .windowed(2)
            .map { Range(it.first(), it.last()) }

        /**
         * Gets the range of table to fill
         * with a buffer
         */
        private fun getFillRange(positions: List<Pos>): Range {
            val minPos = Pos(
                positions.minOf { it.x } - 1,
                positions.minOf { it.y } - 1
            )
            val maxPos = Pos(
                positions.maxOf { it.x } + 1,
                positions.maxOf { it.y } + 1
            )
            return Range(minPos, maxPos)
        }
    }
}