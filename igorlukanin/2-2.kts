// https://adventofcode.com/2020/day/2

import java.nio.file.Files
import java.nio.file.Paths

data class Entry(
        val lower: Int,
        val upper: Int,
        val letter: Char,
        val password: String
) {
    val isValid: Boolean
        get() {
            val first = password[lower - 1]
            val last = password[upper - 1]
            return (first == letter || last == letter) && !(first == letter && last == letter)
        }
}

val entries = Files
        .readAllLines(Paths.get("inputs/2.txt"))
        .map {
            val parts = it.split('-', ' ', ':')
            Entry(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), parts[2][0], parts[4])
        }

val valid = entries.filter { it.isValid }

print(valid.size)