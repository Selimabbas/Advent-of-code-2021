package day01

import java.io.File

fun main() {
    var total = 0
    val values = File("src/main/kotlin/day01/input.txt")
        .readLines()
        .map { it.toInt() }

    for (i in 3 until values.size) {
        if (values[i - 3] < values[i]) total++
    }
    println(total)
}
