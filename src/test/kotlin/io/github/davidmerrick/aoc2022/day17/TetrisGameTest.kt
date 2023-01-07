package io.github.davidmerrick.aoc2022.day17

import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

internal class TetrisGameTest {

    @Test
    fun `Example part 1`() {
        val game = TetrisGame.of(">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>")
        game.dropPieces(2022)
        game.maxHeight shouldBe 3068
    }
}
