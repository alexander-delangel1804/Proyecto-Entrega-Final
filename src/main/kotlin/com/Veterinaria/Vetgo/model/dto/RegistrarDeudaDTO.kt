package com.Veterinaria.Vetgo.model.dto

import java.math.BigDecimal
import java.time.LocalDateTime

data class RegistrarDeudaRequest(
    val idReporte: Int,
    val idServicio: Int,
    val metodoPago: String = "Efectivo"
)

data class RegistrarDeudaResponse(
    val idPago: Int,
    val montoTotal: BigDecimal,
    val estado: String,
    val fechaPago: LocalDateTime
)