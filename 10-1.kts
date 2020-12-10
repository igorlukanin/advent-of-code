// https://adventofcode.com/2020/day/10

import java.lang.Exception
import java.nio.file.Files
import java.nio.file.Paths

fun getRatings(name: String) = Files
        .readAllLines(Paths.get("inputs/$name.txt"))
        .map { it.toInt() }

fun List<Int>.countDiffs(): Pair<Int, Int> {
    var diff1 = 0
    var diff3 = 1

    this.sorted().fold(0) { prev, cur ->
        when (cur - prev) {
            1 -> diff1++
            3 -> diff3++
        }

        cur
    }

    return Pair(diff1, diff3)
}

val (diff1, diff3) = getRatings("10").countDiffs()
println(diff1 * diff3)