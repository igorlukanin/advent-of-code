// https://adventofcode.com/2020/day/4

import java.nio.file.Files
import java.nio.file.Paths

val entries = Files
        .readString(Paths.get(".inputs/4.txt"))
        .split(Regex("\n\n"))
        .map { it
                .split(Regex("\\s|\n"))
                .map { it.split(':') }
                .associate { it[0] to it[1] }
        }

val fields = setOf(
        "byr",
        "iyr",
        "eyr",
        "hgt",
        "hcl",
        "ecl",
        "pid"
)

fun Map<String, String>.isValid() = this.keys.containsAll(fields)

val valid = entries.filter { it.isValid() }

print(valid.size)