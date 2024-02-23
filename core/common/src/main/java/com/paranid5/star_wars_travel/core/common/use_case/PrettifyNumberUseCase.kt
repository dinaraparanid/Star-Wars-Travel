package com.paranid5.star_wars_travel.core.common.use_case

private val NUMBER_REGEX = Regex("(\\d+)")

fun prettifyNumber(string: String) =
    string.replace(NUMBER_REGEX) { prettifyNumberImpl(it.groupValues[1]) }

private fun prettifyNumberImpl(numString: String) =
    numString
        .reversed()
        .chunked(3)
        .joinToString(" ")
        .reversed()