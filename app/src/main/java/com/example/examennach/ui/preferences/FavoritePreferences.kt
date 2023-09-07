package com.example.examennach.ui.preferences

import android.content.Context
import android.content.SharedPreferences

class FavoritePreferences(context: Context) {

    var sharedPreferences: SharedPreferences
    var editor: SharedPreferences.Editor

    companion object {
        const val STATUS_CHECKED_MEMORY_SP = "STATUS_CHECKED_MEMORY_SP"
        private const val IS_CHECKED_ITEM = "IS_CHECKED_ITEM"
    }

    init {
        sharedPreferences =
            context.getSharedPreferences(STATUS_CHECKED_MEMORY_SP, Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
    }

    fun setChecked(isFirstLaunch: Boolean) {
        editor.putBoolean(IS_CHECKED_ITEM, isFirstLaunch)
        editor.commit()
    }

    fun isChecked(): Boolean {
        return sharedPreferences.getBoolean(IS_CHECKED_ITEM, false)
    }
}