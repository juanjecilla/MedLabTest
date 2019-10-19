package com.medlab.medlabtest.data.manager

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.medlab.medlabtest.utils.Properties

class PreferencesManager @SuppressLint("CommitPrefEdits")
constructor(context: Context) {
    private val settings: SharedPreferences
    private val settingsEditor: SharedPreferences.Editor

    var settingsEnded: Boolean
        get() = settings.getBoolean("settingsEnded", false)
        set(settingsEnded) {
            settingsEditor.putBoolean("settingsEnded", settingsEnded)
            settingsEditor.commit()
        }

    var settingsWorldwide: Boolean
        get() = settings.getBoolean("settingsWorldwide", false)
        set(settingsWorldwide) {
            settingsEditor.putBoolean("settingsWorldwide", settingsWorldwide)
            settingsEditor.commit()
        }

    var settingsOrder: Int
        get() = settings.getInt("settingsOrder", 0)
        set(settingsOrder) {
            settingsEditor.putInt("settingsOrder", settingsOrder)
            settingsEditor.commit()
        }

    var settingsOrderField: String?
        get() = settings.getString("settingsOrderField", Properties.FIREBASE_ADDED_AT_TEXT)
        set(settingsOrderField) {
            settingsEditor.putString("settingsOrderField", settingsOrderField)
            settingsEditor.commit()
        }

    init {
        // Initialize SharedPrefences and its editor
        settings = context.getSharedPreferences(PREFS_NAME, 0)
        settingsEditor = settings.edit()
    }

    companion object {
        private const val PREFS_NAME = "SharedPreferences"
    }

}

