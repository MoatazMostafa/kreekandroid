package com.kreek.kreekandroid.common.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Long.toDateTimeString(): String {
    val date = Date(this)
    val format = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
    return format.format(date)
}

fun Long.toDateString(): String {
    return if (this > 0) {
        val date = Date(this)
        val format = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        format.format(date)
    } else {
        ""
    }
}