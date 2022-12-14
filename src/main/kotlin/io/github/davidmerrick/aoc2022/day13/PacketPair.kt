package io.github.davidmerrick.aoc2022.day13

data class PacketPair(
    private val packetA: String,
    private val packetB: String
){
    fun isInOrder() = PacketComparator.compare(packetA!!, packetB!!) == -1

    companion object {
        fun of(lines: List<String>) = PacketPair(lines.first(), lines.last())
    }
}
