// https://adventofcode.com/2020/day/7

import java.nio.file.Files
import java.nio.file.Paths

val rules = Files
        .readAllLines(Paths.get("inputs/7.txt"))
        .map {
            val (bag, parts) = it.split(" bags contain ")
            val contents = parts
                    .split(", ")
                    .filter { it.matches("\\d+ .+ .+ bags?\\.?".toRegex()) }
                    .map {
                        val (count, color1, color2) = it.split(" ")
                        "$color1 $color2" to count.toInt()
                    }
                    .toMap()
            bag to contents
        }
        .toMap()

fun find(rules: Map<String, Map<String, Int>>, needle: String): Set<String> {
    val direct = rules.filter { it.value.containsKey(needle) }.keys
    val indirect = direct.flatMap { find(rules, it) }
    return direct + indirect
}

val options = find(rules, "shiny gold")

println(options.size)