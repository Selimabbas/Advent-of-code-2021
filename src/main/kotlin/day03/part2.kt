package day03

import java.io.File

fun main() {
    val file = File("src/main/kotlin/day03/input.txt").readLines()
    var oxygen = file
    var co2 = file

    for (i in 0..file[0].length) {
        if (oxygen.size > 1) oxygen = oxygen.partition { it[i] == '0' }.biggest()
        if (co2.size > 1) co2 = co2.partition { it[i] == '1' }.smallest()
    }
    println(oxygen[0].toInt(2) * co2[0].toInt(2))
}

fun <T> Pair<List<T>, List<T>>.biggest() = if (first.size > second.size) first else second
fun <T> Pair<List<T>, List<T>>.smallest() = if (first.size < second.size) first else second
