// https://adventofcode.com/2020/day/9

import java.lang.Exception
import java.nio.file.Files
import java.nio.file.Paths

val data = Files
        .readAllLines(Paths.get("inputs/9.txt"))
        .map { it.toLong() }

fun List<Long>.findOdd(preambleSize: Int): Long {
    val preamble = this
            .subList(0, preambleSize)
            .toMutableSet()

    fun Set<Long>.containsComponents(sum: Long) = this
            .mapIndexed { i, first -> this
                    .mapIndexed { j, second ->
                        j > i && sum == first + second
                    }
                    .any { it }
            }
            .any { it }

    var i = preambleSize

    while (i < this.size) {
        if (!preamble.containsComponents(this[i])) return this[i]

        preamble.remove(this[i - preambleSize])
        preamble.add(this[i])
        i++
    }

    throw Exception("Invalid data")
}

print(data.findOdd(25))