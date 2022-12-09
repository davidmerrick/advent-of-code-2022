package io.github.davidmerrick.aoc2022.day4

import io.github.davidmerrick.aoc.util.TestUtil.readLines
import io.github.davidmerrick.aoc.util.filterNotEmpty
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

class Day4Test {

    @Test
    fun `Part 1 example`() {
        readLines(this::class, "example.txt")
            .map { parseLine(it) }
            .count { fullyContains(it.first(), it.last()) } shouldBe 2
    }

    @Test
    fun `Part 1`() {
        readLines(this::class, "day4.txt")
            .map { parseLine(it) }
            .count { fullyContains(it.first(), it.last()) }
            .let { println(it) }
    }

    @Test
    fun `Part 2 example`() {
        readLines(this::class, "example.txt")
            .map { parseLine(it) }
            .count { overlaps(it.first(), it.last()) } shouldBe 4
    }

    @Test
    fun `Part 2 full`() {
        readLines(this::class, "day4.txt")
            .map { parseLine(it) }
            .count { overlaps(it.first(), it.last()) }
            .let { println(it) }
    }

    private fun parseLine(line: String): List<Pair<Int, Int>> {
        return line.split(",")
            .map {
                val split = it.split("-")
                    .filterNotEmpty()
                split[0].toInt() to split[1].toInt()
            }
    }

    private fun fullyContains(a: Pair<Int, Int>, b: Pair<Int, Int>): Boolean {
        return (a.first >= b.first && a.second <= b.second) || (b.first >= a.first && b.second <= a.second)
    }

    private fun overlaps(a: Pair<Int, Int>, b: Pair<Int, Int>): Boolean {
        return (a.first >= b.first && a.first <= b.second) || (b.first >= a.first && b.first <= a.second)
    }
}
