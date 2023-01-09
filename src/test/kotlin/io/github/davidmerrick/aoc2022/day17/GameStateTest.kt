package io.github.davidmerrick.aoc2022.day17

import io.github.davidmerrick.aoc.coordinates.LongPos
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

internal class GameStateTest {

    @Test
    fun `Equals should not compare height`(){
        val a = GameState(1, TetrisPieces.pieces.first(), setOf(LongPos(0,0)), 5)
        val b = a.copy(height = 10)
        a shouldBe b
    }
}
