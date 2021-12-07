package day06

import java.io.File

fun main() {
    val fishes = File("src/main/kotlin/day06/input.txt")
            .readText()
            .split(",")
            .map { it.toInt() }

    val fishesResults = getFishResults()
    var total = 0L
    fishes.forEach { fish -> total += fishesResults[fish] }
    println(total.toString())
}

private fun getFishResults(): List<Long> {
    val fishesValues = mutableListOf<Long>()
    var fishes = mutableListOf<Long>(1, 0, 0, 0, 0, 0, 0, 0, 0)
    val nextFishes = mutableListOf<Long>(0, 0, 0, 0, 0, 0, 0, 0, 0)
    for (day in 0 until 256) {
        for (index in 0..8) {
            if (index == 0) {
                nextFishes[6] += fishes[index]
                nextFishes[8] += fishes[index]
            } else nextFishes[index - 1] += fishes[index]
        }
        fishes = nextFishes.toMutableList()
        nextFishes.clear()
        nextFishes.addAll(listOf(0, 0, 0, 0, 0, 0, 0, 0, 0))
        if (day > 256 - 8) fishesValues.add(fishes.sum())
    }
    fishesValues.reverse()
    return fishesValues
}