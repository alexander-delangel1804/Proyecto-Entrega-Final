package com.Veterinaria.Vetgo.model.dto

data class RegistrarPagoRequest(
    val fkServicio: Int,
    val fkReporte: Int?,
    val metodoPago: String
)