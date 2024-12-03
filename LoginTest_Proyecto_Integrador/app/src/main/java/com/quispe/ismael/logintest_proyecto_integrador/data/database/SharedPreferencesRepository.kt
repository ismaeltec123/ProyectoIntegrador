package com.quispe.ismael.logintest_proyecto_integrador.data.database

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesRepository {

    companion object {
        private lateinit var sharedPreferences: SharedPreferences

        // Claves para las preferencias compartidas
        private const val SHARED_PREFERENCES_KEY = "LOGIN_TEST_SHARED_PREFS"
        private const val IS_TOUR_COMPLETED_KEY = "IS_TOUR_COMPLETED"
        private const val USER_TOKEN_KEY = "USER_TOKEN_KEY"
        private const val DEFAULT_VALUE_FOR_EMPTY = ""
    }

    fun initSharedPreferences(context: Context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE)
    }

    // Estado del tour
    fun isTourCompleted(): Boolean {
        return sharedPreferences.getBoolean(IS_TOUR_COMPLETED_KEY, false)
    }

    fun setTourCompleted(isCompleted: Boolean) {
        sharedPreferences
            .edit()
            .putBoolean(IS_TOUR_COMPLETED_KEY, isCompleted)
            .apply()
    }

    // Token del usuario
    fun getUserToken(): String {
        return sharedPreferences.getString(USER_TOKEN_KEY, DEFAULT_VALUE_FOR_EMPTY) ?: DEFAULT_VALUE_FOR_EMPTY
    }

    fun saveUserToken(token: String) {
        sharedPreferences
            .edit()
            .putString(USER_TOKEN_KEY, token)
            .apply()
    }

    // Limpiar todos los datos (opcional, por ejemplo, para logout)
    fun clearAll() {
        sharedPreferences
            .edit()
            .clear()
            .apply()
    }
}
