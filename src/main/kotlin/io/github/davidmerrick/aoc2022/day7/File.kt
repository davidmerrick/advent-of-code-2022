package io.github.davidmerrick.aoc2022.day7

data class File(val name: String, val size: Int) {
    companion object {
        fun of(str: String) = str.split(" ")
            .let { File(it[0], it[1].toInt()) }
    }
}
