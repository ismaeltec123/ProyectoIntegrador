package com.quispe.ismael.logintest_proyecto_integrador

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.*



interface ApiService {
    @GET("/api/auto/listar")
    fun getParkingSlots(): Call<List<EstacionamientoAuto01>>

    @POST("/api/auto/crear")
    fun createParkingSlot(@Body estacionamiento: EstacionamientoAuto01): Call<EstacionamientoAuto01>

    @DELETE("/api/auto/eliminar/{id}")
    fun deleteParkingSlot(@Path("id") id: Int): Call<Void>
}