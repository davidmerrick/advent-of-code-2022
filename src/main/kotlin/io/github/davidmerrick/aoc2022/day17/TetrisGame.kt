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

private const val LOG_INTERVAL = 5000L

class TetrisGame(private val jet: CircularList<Char>) {
    private val width = 7
    private var table = HashBasedTable.create<Long, Long, Boolean>()
    private val gameStates: MutableList<GameState> = mutableListOf()

    private var floor = 0L

    val piecesHeight: Long
        get() = ((table.asSequence().filter { it.value }.maxOfOrNull { it.row } ?: floor) + 1)

    fun dropPieces(n: Long) {
        var jetIndex = 0
        for (i in 0 until n) {
            // Drop piece
            val piece = TetrisPieces.pieces[(i % TetrisPieces.pieces.size).toInt()]
            val gameState = GameState(jetIndex, piece, getNormalizedBoard())
            if (gameState in gameStates) {
                println("Detected a loop!")
                break
            }
            gameStates.add(gameState)
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

            raiseFloor(getNewFloor(piece, curPos))

            if (i % LOG_INTERVAL == 0L) {
                println("Index: $i. Pieces height: $piecesHeight. Table size: ${table.size()}")
            }
        }

        (n / gameStates.size) * piecesHeight
    }

    /**
     * Computes new floor value, if floor can be raised
     * Returns current value otherwise
     */
    private fun getNewFloor(piece: TetrisPiece, curPos: LongPos): Long {
        val positions = piece.computePositions(curPos)

        // Check if any complete a row
        positions.map { it.y }
            .toSet()
            .sortedDescending()
            .firstOrNull { isRowComplete(it) }?.let {
                return it
            }

        // Check if any rows are blocked off one below
        // i.e.:
        // ..###..
        // ##...##
        positions.map { it.y }.forEach { row ->
            val blocksBelow = table.row(row).map { it.key }.toSet()
                .union(table.row(row - 1).map { it.key }.toSet())
                .size == width
            if (blocksBelow) return row - 1
        }

        return floor
    }

    private fun isRowComplete(row: Long): Boolean {
        return table.rowList(row).count { it } == width
    }

    /**
     * Raises floor and removes rows below from the table
     */
    private fun raiseFloor(row: Long) {
        if (floor == row) return
        println("Raising floor to $row")
        (floor..row).forEach { table.row(it).clear() }
        floor = row
    }

    private fun getNormalizedBoard(): Set<LongPos> {
        return table.asSequence()
            .map { LongPos(it.column, it.row - floor) }
            .toSet()
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
