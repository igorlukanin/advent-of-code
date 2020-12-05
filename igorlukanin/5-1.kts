// https://adventofcode.com/2020/day/5

import java.nio.file.Files
import java.nio.file.Paths

fun String.decode() = Integer.parseInt(this, 2)

fun String.decodeRow() = this
        .replace('F', '0')
        .replace('B', '1')
        .decode()

fun String.decodeColumn() = this
        .replace('L', '0')
        .replace('R', '1')
        .decode()

val passes = Files
        .readAllLines(Paths.get("inputs/5.txt"))
        .map { Pair(it.substring(0, 7).decodeRow(), it.substring(7).decodeColumn()) }

fun Pair<Int, Int>.toSeatId() = this.first * 8 + this.second

val seatIds = passes.map { it.toSeatId() }

print(seatIds.maxBy { it })