package day01

import java.io.File

fun main() {
    var previousValue: Int? = null
    var total = 0
    File("src/main/kotlin/day01/input.txt")
        .readLines()
        .map { it.toInt() }
        .forEach { value ->
            previousValue?.let { if (it < value) total++ }
            previousValue = value
        }
    println(total)
}
