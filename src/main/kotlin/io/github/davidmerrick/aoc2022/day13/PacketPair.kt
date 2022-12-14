package io.github.davidmerrick.aoc2022.day13

data class PacketPair(private val a: String, private val b: String) {

    fun isInOrder(): Boolean {
        while (true) {
            a.popInto()
            b.popInto()

        }
    }

    /**
     * Returns true if they're balanced, false otherwise
     */
    fun compare(a: String, b: String): Boolean {
        // Base case: Two leaves
        if (a.isLeaf() && b.isLeaf()) {
            val aList = a.split(",").map { it.toInt() }
            val bList = b.split(",").map { it.toInt() }
            for (aIdx in aList.indices) {
                if (aIdx >= bList.size) return false
                if (aList[aIdx] > bList[aIdx]) return false
            }
            return true
        } else if (a.isLeaf() && !b.isLeaf()) {
            return compare(a, b.popInto())
        } else if (!a.isLeaf() && b.isLeaf()) {
            return compare(a.popInto(), b)
        }
    }

    companion object {
        fun of(lines: List<String>) = PacketPair(lines.first(), lines.last())
    }
}

internal fun String.isLeaf(): Boolean = !this.contains("[")
internal fun String.popInto(): String {
    require(this.startsWith("[") && this.endsWith("]"))
    return this.drop(1).dropLast(1)
}

