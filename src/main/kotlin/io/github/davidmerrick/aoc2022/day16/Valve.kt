package io.github.davidmerrick.aoc2022.day16

data class Valve(
    val id: String,
    val flowRate: Int,
    val tunnels: List<String>,
    var isOpen: Boolean = false
) {
    companion object {
        fun of(str: String): Valve {
            val split = str.split(" ")
            return Valve(
                split[1],
                split[4].split("=")[1].replace(";", "").toInt(),
                split.drop(9).map { it.replace(",", "") }
            )
        }
    }
}
