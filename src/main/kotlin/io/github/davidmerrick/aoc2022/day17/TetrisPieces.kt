package io.github.davidmerrick.aoc2022.day17

import com.ginsberg.cirkle.circular

object TetrisPieces {
    val pieces = listOf(
        "####",
        ".#.\n###\n.#.",
        "..#\n..#\n###",
        "#\n#\n#\n#",
        "##\n##"
    ).map { TetrisPiece(it) }.circular()
}
