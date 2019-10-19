package com.medlab.medlabtest.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    fun formatDate(seconds: Long): String {
        val date = Date(seconds *1000)
        val sdf = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())

        return sdf.format(date)
    }
}
