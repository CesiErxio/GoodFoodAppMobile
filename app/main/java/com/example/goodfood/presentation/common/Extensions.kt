package com.example.goodfood.presentation.common

import java.lang.StringBuilder
import java.util.concurrent.TimeUnit

fun Double.round(decimals: Int): Double {
    var multiplier = 1.0
    repeat(decimals) { multiplier *= 10 }
    return kotlin.math.round(this * multiplier) / multiplier
}

fun Int.getTimeInMins(): String {
    require(this >= 0) { "Moins d'une minute" }
    val minutes: Long = TimeUnit.MINUTES.toMinutes(this.toLong())

    val sb = StringBuilder(64)
    sb.append(minutes)
    sb.append(" Min ")
    return sb.toString()
}


