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

val seats = (0..127).flatMap { row -> (0..7).map { column -> Pair(row, column) } }

fun Pair<Int, Int>.toSeatId() = this.first * 8 + this.second

val missingSeats = seats
        .subtract(passes)
        .filter { it.first in 12..113 }

print(missingSeats.first().toSeatId())