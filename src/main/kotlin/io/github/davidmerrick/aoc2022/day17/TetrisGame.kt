package io.github.davidmerrick.aoc2022.day17

import com.ginsberg.cirkle.CircularList
import com.ginsberg.cirkle.circular
import com.google.common.collect.HashBasedTable
import io.github.davidmerrick.aoc.coordinates.LongPos
import io.github.davidmerrick.aoc.guava.asSequence
import io.github.davidmerrick.aoc.guava.containsAny
import io.github.davidmerrick.aoc.guava.putAll
import io.github.davidmerrick.aoc.guava.rowList

/**
 * Each rock appears so that its left edge is two units away from the left wall
 * and its bottom edge is three units above the highest rock in the room
 * (or the floor, if there isn't one).
 */

class TetrisGame(private val jet: CircularList<Char>) {
    private val width = 7
    private var table = HashBasedTable.create<Long, Long, Boolean>()

    private var floor = 0L

    val piecesHeight: Long
        get() = ((table.asSequence().filter { it.value }.maxOfOrNull { it.row } ?: floor) + 1)

    fun dropPieces(n: Long) {
        var jetIndex = 0
        (0 until n).forEach { index ->
            // Drop piece
            val piece = TetrisPieces.pieces[(index % TetrisPieces.pieces.size).toInt()]
            val height = piecesHeight + 3
            var curPos = LongPos(2, height)
            while (true) {
                val direction = jet[jetIndex]
                curPos = move(piece, curPos, direction)
                jetIndex = (jetIndex + 1) % jet.size
                if (isAtRest(piece, curPos)) break
                curPos = curPos.copy(y = curPos.y - 1)
            }
            val positions = piece.computePositions(curPos)
            table.putAll(positions, true)

            // If there's a complete row, raise the floor
            positions.map { it.y }
                .toSet()
                .sortedDescending()
                .firstOrNull { isRowComplete(it) }?.let {
                    raiseFloor(it)
                }
        }
    }

    private fun isRowComplete(row: Long): Boolean {
        return table.rowList(row).count { it } == width
    }

    /**
     * Raises floor and removes rows below from the table
     */
    private fun raiseFloor(row: Long) {
        (floor..row).forEach { table.row(it).clear() }
        floor = row
    }

    private fun move(piece: TetrisPiece, curPos: LongPos, direction: Char): LongPos {
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

    private fun canMoveRight(piece: TetrisPiece, curPos: LongPos): Boolean {
        val positionsToCheck = piece.computePositions(curPos.copy(x = curPos.x + 1))
        return (curPos.x + piece.width) < width &&
            !table.containsAny(positionsToCheck, true)
    }

    private fun canMoveLeft(piece: TetrisPiece, curPos: LongPos): Boolean {
        val positionsToCheck = piece.computePositions(curPos.copy(x = curPos.x - 1))
        return curPos.x > 0 &&
            !table.containsAny(positionsToCheck, true)
    }

    /**
     * Computes positions of piece,
     * checks if board contains pieces that are 1 below
     * or if floor was hit
     */
    private fun isAtRest(piece: TetrisPiece, pos: LongPos): Boolean {
        val belowPos = pos.copy(y = pos.y - 1)
        return belowPos.y == floor || table.containsAny(piece.computePositions(belowPos), true)
    }

    companion object {
        fun of(str: String) = TetrisGame(str.trim().toList().circular())
    }
}
