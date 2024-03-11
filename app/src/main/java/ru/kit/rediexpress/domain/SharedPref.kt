package ru.kit.rediexpress.domain

import android.content.Context
import android.content.SharedPreferences

class SharedPref(context: Context) {
    private val pref: SharedPreferences by lazy {
        context.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)
    }

    var isFirstLaunch: Boolean
        get() = pref.getBoolean(KEY_IS_FIRST_LAUNCH, true)
        set(value) {
            pref.edit()
                .putBoolean(KEY_IS_FIRST_LAUNCH, value)
                .apply()
        }

    var email: String
        get() = pref.getString(KEY_USERNAME, null).orEmpty()
        set(value) {
            pref.edit()
                .putString(KEY_USERNAME, value)
                .apply()
        }

    companion object {
        private const val SHARED_PREF = "SHARED_PREF2"
        private const val KEY_IS_FIRST_LAUNCH = "KEY_IS_FIRST_LAUNCH"
        private const val KEY_USERNAME = "KEY_USERNAME"
    }
}