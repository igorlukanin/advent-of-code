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
            val count = password.count { it == letter }
            return count in lower..upper
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