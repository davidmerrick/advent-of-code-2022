package io.github.davidmerrick.aoc2022.day7

data class File(val size: Int, val name: String) {
    companion object {
        fun of(str: String) = str.split(" ")
            .let { File(it[0].toInt(), it[1]) }
    }
}
