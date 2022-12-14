package io.github.davidmerrick.aoc2022.day16

data class Valve(
    val id: String,
    val flowRate: Int,
    val tunnels: List<String>
) {
    companion object {
        fun of(input: String): Valve {
            val split = input.split(" ")
            return Valve(
                split[1],
                split[4].split("=")[1].replace(";", "").toInt(),
                input.substringAfter("valve").substringAfter(" ").split(", ")
            )
        }
    }
}
