package io.github.davidmerrick.aoc2022.day2

data class RockPaperScissorsGame(val opponent: Shape, val you: Shape) {
    val yourScore by lazy { outcomeScore + you.value }

    // 0 if you lost, 3 if the round was a draw, and 6 if you won
    private val outcomeScore by lazy {
        if (opponent.winsAgainst == you::class) {
            0
        } else if (you.winsAgainst == opponent::class) {
            6
        } else 3
    }

    companion object {
        fun of(a: Char, b: Char) = RockPaperScissorsGame(
            ShapeResolver.resolve(a),
            ShapeResolver.resolve(b)
        )
    }
}




