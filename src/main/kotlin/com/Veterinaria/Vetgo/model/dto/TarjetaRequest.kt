package com.Veterinaria.Vetgo.model.dto

data class TarjetaRequest(
    val marca: String,
    val ultimos4: String,
    val expiracion: String,
    val token: String
)

