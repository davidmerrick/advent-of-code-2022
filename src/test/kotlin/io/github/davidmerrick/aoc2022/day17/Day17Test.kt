package io.github.davidmerrick.aoc2022.day17

import io.github.davidmerrick.aoc.util.TestUtil.readText
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

internal class Day17Test {

    @Test
    fun `Example part 1`() {
        val game = TetrisGame.of(">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>")
        game.dropPieces(2022) shouldBe 3068
    }

    @Test
    fun `Part 1 full`() {
        val game = readText(this::class, "day17.txt")
            .let { TetrisGame.of(it) }

        game.dropPieces(2022) shouldBe 3227
    }

    @Test
    fun `Example part 2`() {
        val game = TetrisGame.of(">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>")
        val piecesHeight = game.dropPieces(1_000_000_000_000)
        piecesHeight shouldBe 1_514_285_714_288
    }

    @Test
    fun `Part 2 full`() {
        val game = readText(this::class, "day17.txt")
            .let { TetrisGame.of(it) }

        println(game.dropPieces(1_000_000_000_000))
    }
}
