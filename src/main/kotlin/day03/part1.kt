package day03

import java.io.File

fun main() {
    val file = File("src/main/kotlin/day03/input.txt")
        .readLines()
    val values = MutableList(file[0].length) { 0 }
    file.forEach {
        it.forEachIndexed { index, c ->
            values[index] += c.toInt() - 48
        }
    }

    val gammaRate = values.map { if (it >= file.size / 2) '1' else '0' }.joinToString("")
    val epsilonRate = gammaRate.map { if (it == '1') '0' else '1' }.joinToString("")
    println(gammaRate.toInt(2) * epsilonRate.toInt(2))
}
