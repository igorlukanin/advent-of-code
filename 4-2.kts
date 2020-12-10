// https://adventofcode.com/2020/day/4

import java.nio.file.Files
import java.nio.file.Paths
import java.util.regex.Pattern

val entries = Files
        .readString(Paths.get("inputs/4.txt"))
        .split(Pattern.compile("\n\n"))
        .map { it
                .split(Pattern.compile("\\s|\n"))
                .map { it.split(':') }
                .associate { it[0] to it[1] }
        }

val year = Regex("\\d{4}")
val heightInCm = Regex("\\d+cm")
val heightInIn = Regex("\\d+in")
val colorInRgb = Regex("#[0-9a-f]{6}")
val colorInAbbr = Regex("amb|blu|brn|gry|grn|hzl|oth")
val id = Regex("\\d{9}")

val rules = mapOf<String, (String) -> Boolean>(
        "byr" to { it -> it.matches(year) && it.toInt() in 1920..2002 },
        "iyr" to { it -> it.matches(year) && it.toInt() in 2010..2020 },
        "eyr" to { it -> it.matches(year) && it.toInt() in 2020..2030 },
        "hgt" to { it ->
                it.matches(heightInCm) && it.substringBefore("cm").toInt() in 150..193 ||
                it.matches(heightInIn) && it.substringBefore("in").toInt() in 59..76
        },
        "hcl" to { it -> it.matches(colorInRgb) },
        "ecl" to { it -> it.matches(colorInAbbr) },
        "pid" to { it -> it.matches(id) }
)

fun Map<String, String>.isValid() = rules
        .map { this.containsKey(it.key) && it.value(this.getValue(it.key)) }
        .all { it }

val valid = entries.filter { it.isValid() }

print(valid.size)