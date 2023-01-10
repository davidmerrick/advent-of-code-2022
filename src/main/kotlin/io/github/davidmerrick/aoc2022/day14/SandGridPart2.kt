package io.github.davidmerrick.aoc2022.day14

import com.google.common.collect.HashBasedTable
import io.github.davidmerrick.aoc.coordinates.IntPos
import io.github.davidmerrick.aoc.coordinates.Pos
import io.github.davidmerrick.aoc.coordinates.Range
import io.github.davidmerrick.aoc.guava.asSequence
import io.github.davidmerrick.aoc.guava.fillEmpty
import io.github.davidmerrick.aoc.guava.lookup
import io.github.davidmerrick.aoc.guava.print
import io.github.davidmerrick.aoc.guava.put
import io.github.davidmerrick.aoc2022.day14.SpaceType.AIR
import io.github.davidmerrick.aoc2022.day14.SpaceType.ROCK
import io.github.davidmerrick.aoc2022.day14.SpaceType.SAND

class SandGridPart2(
    rockPositions: List<IntPos>,
    floorModifier: Int?
) {
    private val table: HashBasedTable<Int, Int, SpaceType> = HashBasedTable.create()
    private val floor: Int?

    init {
        rockPositions.forEach { table.put(it, ROCK) }
        floor = floorModifier?.let { rockPositions.maxOf(IntPos::y) + it }
    }

    // Flips to false when sand falls off until infinity
    private var canPourSand = true
    val sandCount
        get() = table.asSequence().count { it.value == SAND }

    fun print(): String {
        padTable()
        return table.print({
            when (it) {
                SAND -> "o"
                ROCK -> "#"
                else -> "."
            }
        })
    }

    /**
     * Pours sand until no more can be poured
     * Then returns the count
     */
    fun pourSand() {
        while (canPourSand) pourGrain()
    }

    private fun pourGrain() {
        var sandPos = sandEntryPoint
        while (true) {
            val nextPos = nextSandPos(sandPos)
            if (nextPos == sandPos) {
                // Sand has come to rest
                table.put(nextPos, SAND)
                if (sandPos == sandEntryPoint) {
                    canPourSand = false
                }
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
    private fun nextSandPos(sandPos: IntPos): IntPos {
        return listOf(
            sandPos.copy(y = sandPos.y + 1),
            sandPos.copy(x = sandPos.x - 1, y = sandPos.y + 1),
            sandPos.copy(x = sandPos.x + 1, y = sandPos.y + 1)
        ).firstOrNull { get(it) == AIR } ?: sandPos
    }

    private fun get(pos: IntPos): SpaceType {
        floor?.let {
            if (pos.y >= it) return ROCK
        }
        return table.lookup(pos) ?: AIR
    }

    /**
     * Pads the table with air so it can be printed
     */
    private fun padTable() {
        val minPos = IntPos(
            table.asSequence().minOf { it.column } - 1,
            table.asSequence().minOf { it.row } - 1
        )
        val maxPos = IntPos(
            table.asSequence().maxOf { it.column } + 1,
            table.asSequence().maxOf { it.row } + 1
        )
        table.fillEmpty(minPos, maxPos, AIR)
    }

    companion object {
        fun of(lines: List<String>, floorModifier: Int? = null): SandGridPart2 {
            val rockPositions = lines.flatMap { parseRanges(it) }
                .flatMap { it.boxify() }
                .toMutableList()
                .apply { add(sandEntryPoint) }

            return SandGridPart2(rockPositions, floorModifier)
        }

        private fun parseRanges(line: String) = line.split(" -> ")
            .map { IntPos.of(it) }
            .windowed(2)
            .map { Range(it.first(), it.last()) }
    }
}
