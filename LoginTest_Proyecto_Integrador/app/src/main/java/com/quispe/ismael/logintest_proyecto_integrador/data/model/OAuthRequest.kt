package com.quispe.ismael.logintest_proyecto_integrador.data.model


data class OAuthRequest(
    val email: String,
    val oauthProvider: String,
    val oauthId: String,
    val nombre: String
)