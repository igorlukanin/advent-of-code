// https://adventofcode.com/2020/day/8

import java.nio.file.Files
import java.nio.file.Paths

val program = Files
        .readAllLines(Paths.get("inputs/8.txt"))
        .map {
            val (instruction, offset) = it.split(' ')
            Pair(instruction, offset.toInt())
        }

fun run(program: List<Pair<String, Int>>): Pair<Int, Boolean> {
    var acc = 0
    val visited = mutableSetOf<Int>()
    var i = 0

    while (!visited.contains(i)) {
        visited.add(i)

        if (i == program.size) return Pair(acc, true)

        when (program[i].first) {
            "nop" -> i++
            "acc" -> {
                acc += program[i].second
                i++
            }
            "jmp" -> i += program[i].second
        }
    }

    return Pair(acc, false)
}

program
        .mapIndexed { i, it -> Pair(i, it) }
        .filter { it.second.first == "nop" || it.second.first == "jmp" }
        .map { it.first }
        .forEach {
            val mutated = program.toMutableList()

            mutated[it] = when (mutated[it].first) {
                "nop" -> Pair("jmp", mutated[it].second)
                "jmp" -> Pair("nop", mutated[it].second)
                else -> throw Exception("Unexpected instruction")
            }

            try {
                val (acc, isTerminated) = run(mutated)

                if (isTerminated) {
                    println(acc)
                }
            }
            catch (e: Exception) {}
        }