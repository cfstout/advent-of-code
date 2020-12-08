package com.cfstout.advent.`2020`

import java.io.File
import kotlin.math.pow

object Day5 {
    @JvmStatic
    fun main(args: Array<String>) {
        val allSeats = (1..993).asIterable().toMutableSet()
        val maxSeat = File("src/main/resources/day_5_input.txt").readLines()
                .map {
                    val row = it.subSequence(0,7)
                            .mapIndexed { index, c -> if (c == 'B') 2.0.pow((6 - index)).toInt() else 0 }
                            .sum()
                    val col = it.subSequence(7,10)
                            .mapIndexed { index, c ->
                                if (c == 'R') 2.0.pow(2- index).toInt() else 0}
                            .sum()

                    println("Row: $row, Col: $col")
                    row * 8 + col
                }
                .onEach {
                    println("ID = $it")
                    allSeats.remove(it)
                }
                .max()
        println("Max seat : ${maxSeat}")
        println("All remaining seats: ${allSeats.sorted()}")
    }
}
