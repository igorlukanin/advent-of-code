// https://adventofcode.com/2020/day/3

import java.nio.file.Files
import java.nio.file.Paths

val entries = Files
        .readAllLines(Paths.get(".inputs/3.txt"))

val count = entries
        .filterIndexed { i, it ->
            val x = (i * 3) % it.length
            it[x] == '#'
        }
        .size

print(count)