package io.github.davidmerrick.aoc2022.day17

import com.ginsberg.cirkle.CircularList
import com.ginsberg.cirkle.circular
import com.google.common.collect.HashBasedTable
import io.github.davidmerrick.aoc.coordinates.Pos
import io.github.davidmerrick.aoc.guava.asSequence
import io.github.davidmerrick.aoc.guava.containsAny
import io.github.davidmerrick.aoc.guava.fillEmpty
import io.github.davidmerrick.aoc.guava.print
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
        get() = table.asSequence().filter { it.value }.maxOfOrNull { it.row } ?: 1

    fun dropPieces(n: Int) {
        var jetIndex = 0
        (0 until n).map { TetrisPieces.pieces[it] }
            .forEach { piece ->
                // Drop piece
                val height = maxHeight + 3
                var curPos = Pos(2, height)
                while (true) {
                    curPos = move(piece, curPos, jet[jetIndex])
                    if (isAtRest(piece, curPos)) break
                    curPos = curPos.copy(y = curPos.y - 1)
                    jetIndex++
                }
                table.putAll(piece.computePositions(curPos), true)
            }
    }

    private fun move(piece: TetrisPiece, curPos: Pos, direction: Char): Pos {
        when (direction) {
            '>' -> if (canMoveRight(piece, curPos)) {
                return curPos.copy(x = curPos.x + 1)
            }
            '<' -> if (canMoveLeft(piece, curPos)) {
                return curPos.copy(x = curPos.x - 1)
            }
        }
        return curPos
    }

    private fun canMoveRight(piece: TetrisPiece, curPos: Pos): Boolean {
        val positionsToCheck = piece.computePositions(curPos.copy(x = curPos.x + 1))
        return (curPos.x + piece.width) < width &&
            !table.containsAny(positionsToCheck, true)
    }

    private fun canMoveLeft(piece: TetrisPiece, curPos: Pos): Boolean {
        val positionsToCheck = piece.computePositions(curPos.copy(x = curPos.x - 1))
        return curPos.x > 0 &&
            !table.containsAny(positionsToCheck, true)
    }

    /**
     * Computes positions of piece,
     * checks if board contains pieces that are 1 below
     * or if floor was hit
     */
    private fun isAtRest(piece: TetrisPiece, pos: Pos): Boolean {
        val belowPos = pos.copy(y = pos.y - 1)
        return belowPos.y == 0 || table.containsAny(piece.computePositions(belowPos), true)
    }

    fun print(): String {
        table.fillEmpty(Pos(0, 0), Pos(width - 1, maxHeight), false)
        return table.print { if (it) "#" else "." }
    }

    companion object {
        fun of(str: String) = TetrisGame(str.trim().toList().circular())
    }
}
