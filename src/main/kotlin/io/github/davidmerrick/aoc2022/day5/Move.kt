package io.github.davidmerrick.aoc2022.day5

data class Move(
    val quantity: Int,
    val source: Int,
    val destination: Int
) {
    companion object {
        fun of(str: String) = with(str.split(" ")) {
            Move(
                quantity = this[1].toInt(),
                source = this[3].toInt(),
                destination = this[5].toInt()
            )
        }
    }
}
