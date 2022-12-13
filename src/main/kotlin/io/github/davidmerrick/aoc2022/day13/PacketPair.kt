package io.github.davidmerrick.aoc2022.day13

data class PacketPair(private val a: String, private val b: String) {

    fun isInOrder(): Boolean {
        TODO()
    }

    companion object {
        fun of(lines: List<String>) = PacketPair(lines.first(), lines.last())
    }
}
