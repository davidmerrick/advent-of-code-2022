package io.github.davidmerrick.aoc2022.day13

import java.util.Stack

data class PacketComparator(
    private val packetA: String? = null,
    private val packetB: String? = null
    ) : Comparator<String> {
    fun isInOrder() = compare(packetA!!, packetB!!) == -1

    /**
     * Recursive compare.
     * Returns -1 if a is less than b
     * 1 if b is greater than a
     * 0 if equal
     */
    override fun compare(a: String, b: String): Int {
        if (a.isInt() && b.isInt()) {
            if (a.toInt() < b.toInt()) return -1
            if (a.toInt() > b.toInt()) return 1
            return 0
        } else if (a.isInt() && b.isList()) {
            return compare("[$a]", b)
        } else if (a.isList() && b.isInt()) {
            return compare(a, "[$b]")
        } else if (a.isList() && b.isList()) {
            val aSplit = splitList(a)
            val bSplit = splitList(b)

            for (i in aSplit.indices) {
                // B ran out of items so A is greater
                if (i >= bSplit.size) return 1
                val result = compare(aSplit[i], bSplit[i])
                if (result != 0) return result
            }
            // A ran out of items so B is greater
            if (bSplit.size > aSplit.size) return -1
            return 0
        }
        error("Unhandled case")
    }

    companion object {
        fun of(lines: List<String>) = PacketComparator(lines.first(), lines.last())

        /**
         * Splits the list into the top-level items
         * i.e. "[1,1], 2, 3" would become listOf("[1,1]", "2", "3")
         */
        fun splitList(str: String): List<String> {
            require(str.isList())
            val sanitized = str.trimBrackets()

            // For balancing parens
            val bracketStack = Stack<Char>()

            var i = 0
            val items = mutableListOf<String>()
            val builder = StringBuilder()
            while (i < sanitized.length) {
                val cur = sanitized[i]
                if (cur == ',' && bracketStack.isEmpty()) {
                    i++
                    continue
                }
                builder.append(cur)
                if (cur == '[') bracketStack.push(cur)
                if (cur == ']') bracketStack.pop()

                // Peek to see if there's more to read
                if (i + 1 < sanitized.length) {
                    if (sanitized[i + 1].isDigit()) {
                        i++
                        continue
                    }
                }

                if (bracketStack.isEmpty()) {
                    items.add(builder.toString())
                    builder.clear()
                }
                i++
            }

            return items
        }
    }
}

internal fun String.trimBrackets(): String {
    require(this.startsWith("[") && this.endsWith("]"))
    return this.drop(1).dropLast(1)
}

internal fun String.isInt() = this.toIntOrNull() != null
internal fun String.isList() = this.startsWith("[") && this.endsWith("]")

