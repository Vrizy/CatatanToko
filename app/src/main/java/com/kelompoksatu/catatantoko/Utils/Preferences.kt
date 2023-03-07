package com.kelompoksatu.catatantoko.Utils

import android.content.Context
import android.content.SharedPreferences

class Preferences(val context: Context) {
    companion object {
        const val USER_PREF = "USER_PREF"
        const val KEY_ID = "KEY_ID"
    }

    var sharedPreferences = context.getSharedPreferences(USER_PREF, 0)

    fun setValues(key: String, value: String) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    var preferencesLogin = context.getSharedPreferences(KEY_ID, 0)
    fun setValueLogin(value: Boolean) {
        val editor: SharedPreferences.Editor = preferencesLogin.edit()
        editor.putBoolean("isLogin", value)
        editor.apply()
    }

    fun setUsername(username: String) {
        sharedPreferences.edit().putString("username", username).apply()
    }

    fun getValueLogin() : Boolean {
        return preferencesLogin.getBoolean("isLogin", false)
    }
}
