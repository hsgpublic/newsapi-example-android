package hsgpublic.example.newsapi.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

fun String.currentLocaledDateString(fromFormat: String): String {
    try {
        val fromFormatter = DateTimeFormatter.ofPattern(fromFormat)
        val dateTime = LocalDateTime.parse(this, fromFormatter)
        val toFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
        val str = dateTime.format(toFormatter)
        return str
    } catch (e: Exception) {
        e.printStackTrace()
        return this
    }
}