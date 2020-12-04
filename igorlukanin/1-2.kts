// https://adventofcode.com/2020/day/1

import java.nio.file.Files
import java.nio.file.Paths
import kotlin.Int.Companion.MAX_VALUE

val numbers = Files
        .readAllLines(Paths.get("inputs/1.txt"))
        .map(Integer::parseInt)
        .sortedDescending()

fun find(numbers: List<Int>): Int {
    val reversed = numbers.asReversed()

    numbers.forEach { first ->
        numbers.forEach { second ->
            reversed.forEach inner@ { last ->
                when (first + second + last) {
                    2020 -> return first * second * last
                    in 2021..MAX_VALUE -> return@inner
                }
            }
        }
    }

    throw Exception("Not found")
}

print(find(numbers))