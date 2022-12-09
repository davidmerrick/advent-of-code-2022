package io.github.davidmerrick.aoc2022.day7

class FileSystem(private val dirMap: Map<String, Directory>) {

    fun getDirectoriesWithMaxSize(n: Int) = getDirectoriesBy { it.size!! < n }

    private fun getDirectoriesBy(predicate: (Directory) -> Boolean): List<Pair<String, Directory>> {
        // Compute sizes of dirs
        size("/")

        return dirMap.filter { predicate(it.value) }.toList()
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

        fun of(lines: List<String>): FileSystem {
            var i = 0
            var curDir = ""
            return buildMap {
                // We're only going to worry about lines with the $
                while (i < lines.size) {
                    val split = lines[i].split(" ")
                    if (split[1] == "cd") {
                        curDir = if (split[2] == "..") {
                            this.getParent(curDir)
                        } else split[2]
                    } else if (split[1] == "ls") {
                        val contents = lines.subList(i + 1, lines.size)
                            .takeWhile { !it.startsWith("$") }
                        this[curDir] = Directory.of(contents)
                        i += contents.size
                    }
                    i++
                }
            }.let { FileSystem(it) }
        }
    }
}

private fun Map<String, Directory>.getParent(dirName: String): String {
    if (dirName == "/") return "/"
    return this
        .asSequence()
        .first { it.value.subDirs.contains(dirName) }
        .let { it.key }
}
