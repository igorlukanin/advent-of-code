// https://adventofcode.com/2020/day/3

import java.nio.file.Files
import java.nio.file.Paths

val entries = Files
        .readAllLines(Paths.get("inputs/3.txt"))

fun List<String>.countTrees(right: Int, down: Int) = this
        .filterIndexed { i, it -> when {
            i % down != 0 -> false
            else -> {
                val x = (i.toDouble() * right / down).toInt() % it.length
                it[x] == '#'
            }
        }}
        .size

val slopes = arrayOf(
        Pair(1, 1),
        Pair(3, 1),
        Pair(5, 1),
        Pair(7, 1),
        Pair(1, 2)
)

val product = slopes
        .map { entries.countTrees(it.first, it.second).toLong() }
        .reduce { acc, i -> acc * i }

print(product)