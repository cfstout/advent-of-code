package com.cfstout.advent.`2020`

import java.io.File

object Day4 {
    @JvmStatic
    fun main(args: Array<String>) {
        val input = File("src/main/resources/day_4_input.txt").readLines()

        var currentPassport = Passport()
        val passports: MutableList<Passport> = mutableListOf()
        for (line in input) {
            if (line.isBlank()) {
                passports.add(currentPassport)
                currentPassport = Passport()
            } else {
                val linePassport = parseLine(line)
                currentPassport = Passport.merge(currentPassport, linePassport)
            }
        }
        passports.add(currentPassport)

        val validPassports = passports.filter { it.isValidP2() }
                .onEach { println(it) }
                .count()
        println(validPassports)
    }

    private fun parseLine(line: String): Passport {
        val lineMap = line.split(' ').map {
            val parts = it.split(':')
            parts[0] to parts[1]
        }.toMap()
        return Passport(
                lineMap.get("byr")?.toInt(),
                lineMap.get("iyr")?.toInt(),
                lineMap.get("eyr")?.toInt(),
                lineMap.get("hgt"),
                lineMap.get("hcl"),
                lineMap.get("ecl"),
                lineMap.get("pid"),
                lineMap.get("cid")?.toInt()
        )
    }
}

data class Passport(val birthYear: Int? = null,
                    val issueYear: Int? = null,
                    val expirationYear: Int? = null,
                    val height: String? = null,
                    val hairColor: String? = null,
                    val eyeColor: String? = null,
                    val passportId: String? = null,
                    val countryId: Int? = null) {
    companion object {
        fun merge(first: Passport, second: Passport): Passport {
            return first.copy(
                    birthYear = second.birthYear ?: first.birthYear,
                    issueYear = second.issueYear ?: first.issueYear,
                    expirationYear = second.expirationYear ?: first.expirationYear,
                    height = second.height ?: first.height,
                    hairColor = second.hairColor ?: first.hairColor,
                    eyeColor = second.eyeColor ?: first.eyeColor,
                    passportId = second.passportId ?: first.passportId,
                    countryId = second.countryId ?: first.countryId
            )
        }
    }

    fun isValid(): Boolean {
        return birthYear != null
                && issueYear != null
                && expirationYear != null
                && height != null
                && hairColor != null
                && eyeColor != null
                && passportId != null
    }

    fun isValidP2(): Boolean {
        return birthYear in 1920..2002
                && issueYear in 2010..2020
                && expirationYear in 2020..2030
                && isHeightValid()
                && isHairColorValid()
                && isEyeColorValid()
                && isPidValid()
    }

    fun isHeightValid(): Boolean {
        if (height == null) {
            return false
        }
        if (height.endsWith("cm")) {
            return height.removeSuffix("cm").toInt() in 150..193
        }
        if (height.endsWith("in")){
            return height.removeSuffix("in").toInt() in 59..76
        }
        return false
    }

    fun isHairColorValid(): Boolean {
        if (hairColor == null) return false
        if (hairColor.startsWith('#')) {
            return hairColor.length == 7 && hairColor.replace("[0-9]|[a-f]".toRegex(), "").length == 1
        } else {
            return false
        }
    }

    fun isEyeColorValid(): Boolean {
        if (eyeColor == null) return false
        return eyeColor.equals("amb")
                || eyeColor.equals("blu")
                || eyeColor.equals("brn")
                || eyeColor.equals("gry")
                || eyeColor.equals("grn")
                || eyeColor.equals("hzl")
                || eyeColor.equals("oth")
    }

    fun isPidValid(): Boolean {
        if (passportId == null) return false
        return passportId.length == 9 && passportId.replace("[0-9]".toRegex(), "").isEmpty()
    }
}
