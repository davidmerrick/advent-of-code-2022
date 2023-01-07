package io.github.davidmerrick.aoc2022.day17

import com.ginsberg.cirkle.CircularList
import com.ginsberg.cirkle.circular
import com.google.common.collect.HashBasedTable
import io.github.davidmerrick.aoc.coordinates.Pos
import io.github.davidmerrick.aoc.guava.asSequence
import io.github.davidmerrick.aoc.guava.contains
import io.github.davidmerrick.aoc.guava.containsAny
import io.github.davidmerrick.aoc.guava.putAll

/**
 * Each rock appears so that its left edge is two units away from the left wall
 * and its bottom edge is three units above the highest rock in the room
 * (or the floor, if there isn't one).
 */

class TetrisGame(private val jet: CircularList<Char>) {
    private val width = 7
    private val table = HashBasedTable.create<Int, Int, Boolean>()

    val maxHeight: Int
        get() = table.asSequence().maxOfOrNull { it.column } ?: 0

    fun dropPieces(n: Int) {
        (0 until n).map { TetrisPieces.pieces[it] }
            .forEach { dropPiece(it) }
    }

    private fun dropPiece(piece: TetrisPiece) {
        val height = table.asSequence().maxOfOrNull { it.column } ?: 0 + 3
        var jetIndex = 0
        var curPos = Pos(2, height)
        while (!isAtRest(piece, curPos)) {
            when (jet[jetIndex]) {
                '>' -> if (canMoveRight(piece, curPos)) curPos = curPos.copy(x = curPos.x + 1)
                '<' -> if (canMoveLeft(piece, curPos)) curPos = curPos.copy(x = curPos.x - 1)
            }
            curPos = curPos.copy(y = curPos.y - 1)
            jetIndex++
        }
        table.putAll(piece.computePositions(curPos), true)
    }

    private fun canMoveRight(piece: TetrisPiece, curPos: Pos): Boolean {
        val positionsToCheck = piece.computePositions(curPos.copy(x = curPos.x + 1))
        return curPos.x + piece.width < width - 1 &&
            !table.containsAny(positionsToCheck)
    }

    private fun canMoveLeft(piece: TetrisPiece, curPos: Pos): Boolean {
        val positionsToCheck = piece.computePositions(curPos.copy(x = curPos.x - 1))
        return curPos.x > 0 &&
            !table.containsAny(positionsToCheck)
    }

    /**
     * Computes positions of piece,
     * checks if board contains pieces that are 1 below
     * or if floor was hit
     */
    private fun isAtRest(piece: TetrisPiece, pos: Pos): Boolean {
        val belowPos = pos.copy(y = pos.y - 1)
        return belowPos.y == 0 || table.containsAny(piece.computePositions(belowPos))
    }

    companion object {
        fun of(str: String) = TetrisGame(str.toList().circular())
    }
}
