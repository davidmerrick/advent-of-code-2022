package io.github.davidmerrick.aoc2022.day16

data class Valve(
    val id: String,
    val flowRate: Int,
    val tunnels: List<String>
) {
    companion object {
        fun of(str: String): Valve {
            val split = str.split(" ")
            return Valve(
                split[1],
                split[4].split("=")[1].replace(";", "").toInt(),
                split.drop(8).map { it.replace(",", "") }
            )
        }
    }
}
