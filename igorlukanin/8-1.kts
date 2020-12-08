// https://adventofcode.com/2020/day/8

import java.nio.file.Files
import java.nio.file.Paths

val program = Files
        .readAllLines(Paths.get("inputs/8.txt"))
        .map {
            val (instruction, offset) = it.split(' ')
            Pair(instruction, offset.toInt())
        }

fun run(program: List<Pair<String, Int>>): Int {
    var acc = 0
    val visited = mutableSetOf<Int>()
    var i = 0

    while (!visited.contains(i)) {
        visited.add(i)

        when (program[i].first) {
            "nop" -> i++
            "acc" -> {
                acc += program[i].second
                i++
            }
            "jmp" -> i += program[i].second
        }
    }

    return acc
}

println(run(program))