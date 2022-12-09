package io.github.davidmerrick.aoc2022.day7

class FileSystem(private val dirMap: Map<String, Directory>) {

    fun getDirectoriesWithMaxSize(n: Int) = getDirectoriesBy { it.size!! < n }

    private fun getDirectoriesBy(predicate: (Directory) -> Boolean): List<Directory> {
        // Compute sizes of dirs
        size("/")

        return dirMap.values.filter { predicate(it) }.toList()
    }

    /**
     * Recursively computes size of directory
     */
    private fun size(dirName: String): Int {
        // Base case
        val dir = dirMap[dirName]!!
        dir.size?.let { return it }

        if (dir.subDirs.isEmpty()) return dir.files.sumOf { it.size }

        dir.size = dir.files.sumOf { it.size } + dir.subDirs.sumOf { size(it) }
        return dir.size!!
    }

    companion object {
        fun of(lines: List<String>): FileSystem {
            return buildMap<String, Directory> {
                for (line in lines) {
                    println("foo")
                }

                TODO()
            }.let { FileSystem(it) }
        }

    }
}
