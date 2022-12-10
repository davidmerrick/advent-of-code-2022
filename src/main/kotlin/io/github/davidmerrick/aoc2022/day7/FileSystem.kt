package io.github.davidmerrick.aoc2022.day7

class FileSystem(private val dirMap: Map<String, Directory>) {

    fun getDirectoriesWithMaxSize(n: Int) = getDirectoriesBy { it.size!! < n }

    fun getDirectoriesBy(predicate: (Directory) -> Boolean): List<Pair<String, Directory>> {
        // Compute sizes of dirs
        size("")

        return dirMap.filter { predicate(it.value) }.toList()
    }

    fun totalUsed(): Int {
        return size("")
    }

    /**
     * Recursively computes size of directory
     */
    private fun size(dirName: String): Int {
        // Base case
        val dir = dirMap[dirName]!!
        dir.size?.let { return it }

        if (dir.subDirs.isEmpty()) {
            dir.size = dir.files.sumOf { it.size }
            return dir.size!!
        }

        dir.size = dir.files.sumOf { it.size } + dir.subDirs.sumOf { size(it) }
        return dir.size!!
    }

    companion object {

        private fun getParent(cwd: String): String {
            if (cwd == "/") return ""
            return cwd.substring(0, cwd.lastIndexOf("/"))
        }

        fun of(lines: List<String>): FileSystem {
            var i = 0
            var cwd = ""
            return buildMap {
                // We're only going to worry about lines with the $
                while (i < lines.size) {
                    val split = lines[i].split(" ")
                    if (split[1] == "cd") {
                        cwd = when (split[2]) {
                            ".." -> getParent(cwd)
                            "/" -> ""
                            else -> "$cwd/${split[2]}"
                        }
                    } else if (split[1] == "ls") {
                        val contents = lines.subList(i + 1, lines.size)
                            .takeWhile { !it.startsWith("$") }
                        this[cwd] = Directory.of(cwd, contents)
                        i += contents.size
                    }
                    i++
                }
            }.let { FileSystem(it) }
        }
    }
}
