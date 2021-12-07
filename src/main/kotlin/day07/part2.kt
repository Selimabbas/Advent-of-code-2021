package day07

import java.io.File
import kotlin.math.abs

fun main() {
    val positions = File("src/main/kotlin/day07/input.txt")
            .readText()
            .split(',')
            .map { it.toInt() }

    val max = positions.maxOrNull()!!

    var minFuel = -1
    for (value in 0..max) {
        var total = 0
        positions.forEach {
            for (fuel in 0..abs(it - value)) total += fuel
        }
        if (minFuel == -1 || minFuel > total) minFuel = total
    }
    println(minFuel)
}