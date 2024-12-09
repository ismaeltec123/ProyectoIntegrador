package com.quispe.ismael.logintest_proyecto_integrador.data.network

import com.quispe.ismael.logintest_proyecto_integrador.data.model.EstacionamientoAuto01
import com.quispe.ismael.logintest_proyecto_integrador.data.model.Estacionamiento
import com.quispe.ismael.logintest_proyecto_integrador.data.model.EstacionamientoDTO
import com.quispe.ismael.logintest_proyecto_integrador.data.model.OAuthRequest
import com.quispe.ismael.logintest_proyecto_integrador.data.model.PreferenciaDTO
import com.quispe.ismael.logintest_proyecto_integrador.data.model.Usuario
import com.quispe.ismael.logintest_proyecto_integrador.data.model.UsuarioDTO
import com.quispe.ismael.logintest_proyecto_integrador.data.model.VehiculoDTO
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

    @GET("/api/estacionamientos") // Cambia este endpoint según tu API
    fun getEstacionamientos(): Call<List<Estacionamiento>>


    @GET("/api/estacionamientos/capacidad-disponible")
    fun getEstacionamientosDisponibles(): Call<List<EstacionamientoDTO>>

    @POST("/oauth/register")
    fun registerOAuth(@Body oAuthRequest: OAuthRequest): Call<Usuario>

    @GET("/api/usuarios/{id}")
    fun getUsuario(@Path("id") id: Int): Call<Usuario>

    @PUT("/api/usuarios/{id}")
    fun updateUsuario(@Path("id") id: Int, @Body usuarioDTO: UsuarioDTO): Call<Void>

    @PUT("/api/usuarios/{userId}/preferencia")
    fun actualizarPreferencia(
        @Path("userId") userId: Int,
        @Body preferenciaDTO: PreferenciaDTO
    ): Call<Void>

    // Obtener todos los vehículos
    //@GET("/api/vehiculos")
    //fun getVehiculos(): Call<List<VehiculoDTO>>

    @GET("/api/vehiculos")
    fun getVehiculos(@Query("usuarioId") userId: Int): Call<List<VehiculoDTO>>

    @POST("/api/vehiculos")
    fun createVehiculo(@Body vehiculoDTO: VehiculoDTO): Call<VehiculoDTO>

    @PUT("/api/vehiculos/{id}")
    fun updateVehiculo(@Path("id") id: Int, @Body vehiculoDTO: VehiculoDTO): Call<VehiculoDTO>

    // Buscar un vehículo por ID
    @GET("/api/vehiculos/{id}")
    fun getVehiculo(@Path("id") id: Int): Call<VehiculoDTO>


    @GET("/api/vehiculos/usuario/{usuarioId}")
    fun getVehiculosPorUsuario(@Path("usuarioId") userId: Int): Call<List<VehiculoDTO>>

    // Eliminar un vehículo por ID
    @DELETE("/api/vehiculos/{id}")
    fun eliminarVehiculo(@Path("id") id: Int): Call<Void>
}

