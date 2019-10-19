package com.medlab.medlabtest.utils

object NameUtils {

    fun capitalize(text: String?): String {
        return if (text != null) {
            if (text.length > 2) {
                text.substring(0, 1).toUpperCase() + text.substring(1)
            } else {
                text
            }
        } else {
            ""
        }
    }

    fun parseSpaces(text: String): String {
        return text.replace("-", " ")
    }

}
