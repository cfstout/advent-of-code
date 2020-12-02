@file:Suppress("PackageName")

package com.cfstout.advent.`2020`

import java.io.File

object Day2 {
    @JvmStatic
    fun main(args: Array<String>) {
        val validPasswords = File("src/main/resources/day_2_input.txt").readLines()
                .map { PasswordEntry.fromString(it) }
                .filter { it.isValidPartTwo() }
                .onEach { println("$it is valid? ${it.isValid()}")
                    println("Character 1 ${it.password[it.minOccurrence-1]}, Character 2 ${it.password[it.maxOccurrence-1]}")
                }
                .size

        println("$validPasswords valid passwords")
    }
}

data class PasswordEntry(val minOccurrence: Int,
                         val maxOccurrence: Int,
                         val character: Char,
                         val password: String) {
    companion object {
        val lineRegex = "(\\d+)-(\\d+) ([a-z]): ([a-z]*)".toRegex()
        fun fromString(stringFromFile: String): PasswordEntry {
            val result = lineRegex.matchEntire(stringFromFile) ?: throw IllegalArgumentException("Corrupted line! $stringFromFile")
            return PasswordEntry(result.groupValues[1].toInt(), result.groupValues[2].toInt(), result.groupValues[3][0], result.groupValues[4])
        }
    }

    fun isValid(): Boolean {
        val charactersInPassword = password.filter { it == character }.length
        return charactersInPassword in minOccurrence..maxOccurrence
    }

    fun isValidPartTwo():Boolean {
        val firstIsCharacter = password[minOccurrence-1] == character
        val secondIsCharacter = password[maxOccurrence-1] == character
        return firstIsCharacter.xor(secondIsCharacter)
    }
}

