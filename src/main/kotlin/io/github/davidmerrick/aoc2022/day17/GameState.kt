package io.github.davidmerrick.aoc2022.day17

import io.github.davidmerrick.aoc.coordinates.LongPos

data class GameState(
    val jetIndex: Int,
    val piece: TetrisPiece,
    val board: Set<LongPos>,
    var height: Long
) {
    override fun equals(other: Any?): Boolean {
        if (other !is GameState) return false
        return other.jetIndex == this.jetIndex &&
            other.board == this.board &&
            other.piece == this.piece
    }

    override fun hashCode(): Int {
        val prime = 31
        return jetIndex * prime + piece.hashCode() * prime + board.hashCode() * prime
    }
}
