package day06

import java.io.File

fun main() {
    val fishes = File("src/main/kotlin/day06/input.txt")
            .readText()
            .split(",")
            .map { it.toInt() }
            .toMutableList()

    for (day in 0 until 80) {
        for (i in 0 until fishes.size) {
            val fish = fishes[i]
            if (fish == 0) {
                fishes[i] = 6
                fishes.add(8)
            } else fishes[i]--
        }
    }
    println(fishes.size)
}