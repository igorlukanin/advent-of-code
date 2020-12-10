// https://adventofcode.com/2020/day/6

import java.nio.file.Files
import java.nio.file.Paths

val entries = Files
        .readString(Paths.get("inputs/6.txt"))
        .split(Regex("\n\n"))
        .map { it
                .split(Regex("\n"))
                .map { it.toCharArray().toList() }
                .let { it.first().filter { question -> it.all { it.contains(question) } } }
        }

print(entries.map { it.size }.sum())