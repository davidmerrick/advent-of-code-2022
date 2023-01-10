package io.github.davidmerrick.aoc2022.day17

import io.github.davidmerrick.aoc.coordinates.LongPos
import io.kotlintest.matchers.collections.shouldContainAll
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

internal class TetrisPieceTest {


    @Test
    fun `Compute positions of flat piece`() {
        val positions = TetrisPiece("####").computePositions(LongPos(0, 0))
        positions.size shouldBe 4
        val xList = positions.map { it.x }
        xList.max() shouldBe 3
        val yList = positions.map { it.y }.toSet()
        yList.size shouldBe 1
        yList.contains(0) shouldBe true
    }

    @Test
    fun `Compute positions of square piece`() {
        val positions = TetrisPiece("##\n##").computePositions(LongPos(0, 0))
        positions.size shouldBe 4
        val xList = positions.map { it.x }
        xList shouldContainAll listOf(0, 1)
        val yList = positions.map { it.y }.toSet()
        yList shouldContainAll listOf(0, 1)
    }

    @Test
    fun `Compute positions of vertical piece`() {
        val positions = TetrisPiece("#\n#\n#\n#").computePositions(LongPos(0, 0))
        positions.size shouldBe 4
        val xList = positions.map { it.x }
        xList shouldContainAll listOf(0)
        val yList = positions.map { it.y }.toSet()
        yList shouldContainAll (0..3).toList()
    }

    @Test
    fun `Compute positions of plus piece`() {
        val positions = TetrisPiece(".#.\n###\n.#.").computePositions(LongPos(0, 0))
        positions.size shouldBe 5
        positions.shouldContainAll(
            LongPos(1, 0),
            LongPos(0, 1),
            LongPos(1, 1),
            LongPos(2, 1),
            LongPos(1, 2),
        )
    }
}
