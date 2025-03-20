package br.com.victorcs.querosermb.core.extensions

import br.com.victorcs.querosermb.core.constants.EMPTY
import java.text.SimpleDateFormat
import java.util.Locale

private const val DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSS'Z'"
private const val OUTPUT_DATE_FORMAT = "dd/MM/yyyy"

private val inputDateFormat = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
private val outputDateFormat = SimpleDateFormat(OUTPUT_DATE_FORMAT, Locale.getDefault())

fun String.toFormatedDate(): String =
    this.let {
        try {
            inputDateFormat.parse(it)?.let { parsedDate ->
                outputDateFormat.format(parsedDate)
            }
        } catch (e: Exception) {
            null
        }
    } ?: EMPTY