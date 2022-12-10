package io.github.davidmerrick.aoc2022.day7

data class Directory(val files: List<File>, val subDirs: List<String>, var size: Int? = null) {
    companion object {
        fun of(cwd: String, lines: List<String>): Directory {
            val files = lines.filter { it[0].isDigit() }
                .map { File.of(it) }
                .toList()
            val subDirs = lines.filter { it.startsWith("dir") }
                .map { "$cwd/${it.split(" ")[1]}" }
            return Directory(files, subDirs)
        }
    }
}
