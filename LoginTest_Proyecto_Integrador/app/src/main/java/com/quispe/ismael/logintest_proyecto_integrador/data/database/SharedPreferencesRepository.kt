package com.quispe.ismael.logintest_proyecto_integrador.data.database

import android.content.Context
import android.content.SharedPreferences
import android.util.Log

class SharedPreferencesRepository(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE)

    companion object {
        // Claves para las preferencias compartidas
        private const val SHARED_PREFERENCES_KEY = "LOGIN_TEST_SHARED_PREFS"
        private const val IS_TOUR_COMPLETED_KEY = "IS_TOUR_COMPLETED"
        private const val USER_TOKEN_KEY = "USER_TOKEN_KEY"
        private const val USER_ID_KEY = "USER_ID"
        private const val DEFAULT_VALUE_FOR_EMPTY = ""
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

    // ID del usuario
    fun obtenerIdUsuarioDesdePreferencias(): Int {

        return sharedPreferences.getInt(USER_ID_KEY, -1)
    }

    fun guardarIdUsuarioEnPreferencias(userId: Int) {
        sharedPreferences
            .edit()
            .putInt(USER_ID_KEY, userId)
            .apply()
    }

    fun guardarEspacioOcupado(espacioId: Int) {
        sharedPreferences.edit()
            .putInt("ESPACIO_OCUPADO", espacioId)
            .apply()
    }

    fun obtenerEspacioOcupado(): Int? {
        val espacioId = sharedPreferences.getInt("ESPACIO_OCUPADO", -1)
        return if (espacioId == -1) null else espacioId
    }

    fun liberarEspacio() {
        sharedPreferences.edit()
            .remove("ESPACIO_OCUPADO")
            .apply()
    }

    fun getPreferenciaEstacionamiento(): Int {
        return sharedPreferences.getInt("PREFERENCIA_ESTACIONAMIENTO", -1)
    }

    fun guardarPreferenciaEstacionamiento(preferencia: Int) {
        sharedPreferences.edit()
            .putInt("PREFERENCIA_ESTACIONAMIENTO", preferencia)
            .apply()
    }
    fun guardarMovimientoId(movimientoId: Int) {
        sharedPreferences.edit()
            .putInt("MOVIMIENTO_ID", movimientoId)
            .apply()
    }

    fun getMovimientoId(): Int {
        return sharedPreferences.getInt("MOVIMIENTO_ID", -1)
    }

    fun liberarMovimientoId() {
        sharedPreferences.edit()
            .remove("MOVIMIENTO_ID")
            .apply()
    }


    // Limpiar todos los datos (por ejemplo, para logout)
    fun clearAll() {
        sharedPreferences
            .edit()
            .clear()
            .apply()
    }

}
