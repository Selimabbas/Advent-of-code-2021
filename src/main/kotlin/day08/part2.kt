package day08

import java.io.File
import java.lang.Exception

fun main() {
    var total = 0
    File("src/main/kotlin/day08/input.txt")
            .readLines()
            .map { line ->
                line.split('|').map { it.split(' ').filter { numbers -> numbers.isNotEmpty() } }
            }.forEach { line ->
                val one = line[0].first { it.length == 2 }
                val four = line[0].first { it.length == 4 }
                val seven = line[0].first { it.length == 3 }
                val eight = line[0].first { it.length == 7 }

                val zeroOrSixOrNine = line[0].filter { it.length == 6 }.toMutableList()
                val twoOrThreeOrFive = line[0].filter { it.length == 5 }.toMutableList()
                val six = zeroOrSixOrNine.first { !it.contains(one[0]) || !it.contains(one[1]) }
                zeroOrSixOrNine.remove(six)
                val zero = zeroOrSixOrNine.first { zero ->
                    four.forEach { if (!zero.contains(it)) return@first true }
                    return@first false
                }
                zeroOrSixOrNine.remove(zero)
                val nine = zeroOrSixOrNine[0]
                val two = twoOrThreeOrFive.first { two ->
                    two.forEach { if (!nine.contains(it)) return@first true }
                    return@first false
                }
                twoOrThreeOrFive.remove(two)
                val three = twoOrThreeOrFive.first { three ->
                    three.forEach { if (!six.contains(it)) return@first true }
                    return@first false
                }
                twoOrThreeOrFive.remove(three)
                val five = twoOrThreeOrFive[0]

                val number = line[1].map {
                    when {
                        it.sameAs(zero) -> '0'
                        it.sameAs(one) -> '1'
                        it.sameAs(two) -> '2'
                        it.sameAs(three) -> '3'
                        it.sameAs(four) -> '4'
                        it.sameAs(five) -> '5'
                        it.sameAs(six) -> '6'
                        it.sameAs(seven) -> '7'
                        it.sameAs(eight) -> '8'
                        it.sameAs(nine) -> '9'
                        else -> throw Exception("error $it")
                    }
                }.joinToString("").toInt()
                total += number
            }
    println(total)
}

private fun String.sameAs(number: String): Boolean {
    if (length != number.length) return false
    forEach {
        if (!number.contains(it)) return false
    }
    return true
}