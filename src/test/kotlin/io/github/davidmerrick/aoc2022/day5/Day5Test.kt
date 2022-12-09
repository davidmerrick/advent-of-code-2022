package io.github.davidmerrick.aoc2022.day5

import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

class Day5Test {

    @Test
    fun `Part 1 example`() {
        val fileName = "example.txt"
        val stacks = StackParser.parse(fileName)
        val moves = MoveParser.parse(fileName)
        for (move in moves) {
            executeMove(stacks, move)
        }
        printTop(stacks) shouldBe "CMZ"
    }

    @Test
    fun `Part 1`() {
        val fileName = "day5.txt"
        val stacks = StackParser.parse(fileName)
        val moves = MoveParser.parse(fileName)
        for (move in moves) {
            executeMove(stacks, move)
        }
        println(printTop(stacks))
    }

    @Test
    fun `Part 2 example`() {
        val fileName = "example.txt"
        val stacks = StackParser.parse(fileName)
        val moves = MoveParser.parse(fileName)
        for (move in moves) {
            executePart2Move(stacks, move)
        }
        printTop(stacks) shouldBe "MCD"
    }

    @Test
    fun `Part 2 full`() {
        val fileName = "day5.txt"
        val stacks = StackParser.parse(fileName)
        val moves = MoveParser.parse(fileName)
        for (move in moves) {
            executePart2Move(stacks, move)
        }
        println(printTop(stacks))
    }

    private fun printTop(stacks: List<ArrayDeque<Char>>): String {
        return stacks.joinToString("") { it.first().toString() }
    }

    private fun executeMove(stacks: List<ArrayDeque<Char>>, move: Move) {
        repeat(move.quantity) {
            val item = stacks[move.source - 1].removeFirst()
            stacks[move.destination - 1].addFirst(item)
        }
    }

    private fun executePart2Move(stacks: List<ArrayDeque<Char>>, move: Move) {
        val items = stacks[move.source - 1].take(move.quantity)
        repeat(move.quantity) {
            stacks[move.source - 1].removeFirst()
        }
        items.reversed().forEach {
            stacks[move.destination - 1].addFirst(it)
        }
    }
}
