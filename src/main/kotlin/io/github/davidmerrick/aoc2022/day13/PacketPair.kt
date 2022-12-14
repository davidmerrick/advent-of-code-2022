package io.github.davidmerrick.aoc2022.day13

data class PacketPair(private val a: String, private val b: String) {

    fun isInOrder() = compare(a, b) == -1

    /**
     * Recursive compare.
     * Returns -1 if a is less than b
     * 1 if b is greater than a
     * 0 if equal
     */
    fun compare(a: String, b: String): Int {
        if (a.isInt() != null && b.isInt()) {
            if (a.toInt() < b.toInt()) return -1
            if (a.toInt() > b.toInt()) return 1
            return 0
        } else if (a.isList() && b.isList()) {
            // Iterate the list
            val aSplit = splitList(a)
            val bSplit = splitList(b)

            // Read each list item


            // Compare on each

            TODO()
        } else if (a.isInt() && b.isList()) {
            return compare("[$a]", b)
        } else if (a.isList() && b.isInt()) {
            return compare(a, "[$b]")
        } else error("Unhandled case")
    }

    /**
     * Splits the list into the top-level items
     * i.e. "[1,1], 2, 3" would become listOf("[1,1]", "2", "3")
     */
    private fun splitList(string: String): List<String> {
        TODO()

        // Balance the parens using a stack
    }

    companion object {
        fun of(lines: List<String>) = PacketPair(lines.first(), lines.last())
    }
}

internal fun String.popInto(): String {
    require(this.startsWith("[") && this.endsWith("]"))
    return this.drop(1).dropLast(1)
}

internal fun String.isInt() = this.toIntOrNull() != null
internal fun String.isList() = this.startsWith("[") && this.endsWith("]")

