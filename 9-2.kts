// https://adventofcode.com/2020/day/9

import java.lang.Exception
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.math.max
import kotlin.math.sign

val data = Files
        .readAllLines(Paths.get("inputs/9.txt"))
        .map { it.toLong() }

fun findOdd(data: List<Long>, preambleSize: Int): Long {
    val preamble = data
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

    while (i < data.size) {
        if (!preamble.containsComponents(data[i])) return data[i]

        preamble.remove(data[i - preambleSize])
        preamble.add(data[i])
        i++
    }

    throw Exception("Invalid data")
}

fun List<Long>.findRollingComponents(sum: Long): List<Long> {
    val offset = 2
    var start = 0
    var end = start + offset

    while (end <= this.size) {
        val rollingSum = this.subList(start, end).sum()

        when ((rollingSum - sum).sign) {
            -1 -> end++
            1 -> {
                start++
                end = max(start + offset, end)
            }
            0 -> return this.subList(start, end)
        }
    }

    throw Exception("Invalid data")
}

val odd = findOdd(data, 25)
val components = data.findRollingComponents(odd)

println(components.min()!! + components.max()!!)