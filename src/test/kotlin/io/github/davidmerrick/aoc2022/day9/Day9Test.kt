package io.github.davidmerrick.aoc2022.day9

import io.github.davidmerrick.aoc.coordinates.Move
import io.github.davidmerrick.aoc.util.TestUtil.lineSequence
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

private const val PART_2_SEGMENT_COUNT = 10

class Day9Test {

    @Test
    fun `Part 1 example`() {
        val moves = lineSequence(this::class, "example.txt")
            .flatMap { toMoves(it) }
            .toList()

        val rope = Rope()
        rope.swing(moves)
        rope.visitedCount shouldBe 13
    }

    @Test
    fun `Part 1 full`() {
        val moves = lineSequence(this::class, "day9.txt")
            .flatMap { toMoves(it) }
            .toList()

        val rope = Rope()
        rope.swing(moves)
        rope.print()
        println(rope.visitedCount)
    }

    @Test
    fun `Part 2 example`() {
        val moves = lineSequence(this::class, "example.txt")
            .flatMap { toMoves(it) }
            .toList()

        val rope = Rope(PART_2_SEGMENT_COUNT)
        rope.swing(moves)
        rope.visitedCount shouldBe 1
    }

    @Test
    fun `Part 2 example 2`() {
        val moves = lineSequence(this::class, "example2.txt")
            .flatMap { toMoves(it) }
            .toList()

        val rope = Rope(PART_2_SEGMENT_COUNT)
        rope.swing(moves)
        rope.visitedCount shouldBe 36
    }

    @Test
    fun `Part 2 full`() {
        val moves = lineSequence(this::class, "day9.txt")
            .flatMap { toMoves(it) }
            .toList()

        val rope = Rope(PART_2_SEGMENT_COUNT)
        rope.swing(moves)
        println(rope.visitedCount)
    }
}

private fun toMoves(str: String): List<Move> {
    val split = str.split(" ")
    val magnitude = split[1].toInt()
    return (0 until magnitude).map {
        when (split[0]) {
            "R" -> Move(1, 0)
            "L" -> Move(-1, 0)
            "U" -> Move(0, 1)
            "D" -> Move(0, -1)
            else -> throw RuntimeException("Invalid direction $split[0]")
        }
    }
}
