package io.github.davidmerrick.aoc2022.day2

object ShapeResolver {
    private val shapes = listOf(Rock, Paper, Scissors)
    private val shapeMap = shapes
        .flatMap { it.aliases.map { alias -> alias to it } }
        .toMap()

    /**
     * Resolves a shape by the needed outcome for part 2
     */
    fun resolveByOutcome(opponent: Char, outcome: Char): Shape {
        val opponentShape = resolve(opponent)
        return when (outcome) {
            // Lose
            'X' -> shapes.first { it::class == opponentShape.winsAgainst }
            // Draw
            'Y' -> opponentShape
            // Win
            'Z' -> shapes.first { it.winsAgainst == opponentShape::class }
            else -> throw IllegalArgumentException("Invalid outcome: $outcome")
        }
    }

    fun resolve(a: Char) = shapeMap[a]!!
}
