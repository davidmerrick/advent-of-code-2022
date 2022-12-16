package io.github.davidmerrick.aoc2022.day14

import com.google.common.collect.HashBasedTable
import io.github.davidmerrick.aoc.coordinates.Pos
import io.github.davidmerrick.aoc.coordinates.Range
import io.github.davidmerrick.aoc.guava.asSequence
import io.github.davidmerrick.aoc.guava.fill
import io.github.davidmerrick.aoc.guava.lookup
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

    /**
     * Pours sand until no more can be poured
     * Then returns the count
     */
    fun pourSand(): Int {
        while (canPourSand) pourGrain()
        return table.asSequence().count { it.value == SAND }
    }

    private fun pourGrain() {
        var sandPos = sandEntryPoint
        while (true) {
            val nextPos = nextSandPos(sandPos)
            if (nextPos == null) {
                // Sand has fallen into the ether
                canPourSand = false
                return
            }
            if (nextPos == sandPos) {
                // Sand has come to rest
                table.put(nextPos, SAND)
                return
            }
            sandPos = nextPos
        }
    }

    /**
     * If there's a next move, returns it
     * If the sand should come to rest, returns the current position
     * If the sand falls into the ether, returns null
     */
    private fun nextSandPos(sandPos: Pos): Pos? {
        listOf(
            sandPos.copy(y = sandPos.y + 1),
            sandPos.copy(x = sandPos.x - 1, y = sandPos.y + 1),
            sandPos.copy(x = sandPos.x + 1, y = sandPos.y + 1)
        ).forEach {
            val value = table.lookup(it) ?: return null
            if (value == AIR) return it
        }
        return sandPos
    }

    companion object {
        fun of(lines: List<String>): SandGrid {
            val table = HashBasedTable.create<Int, Int, SpaceType>()
            val positions = lines.flatMap { parseRanges(it) }
                .flatMap { it.positions }
                .toMutableList()
                .apply { add(sandEntryPoint) }
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