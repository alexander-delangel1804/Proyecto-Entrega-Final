package com.Veterinaria.Vetgo.model.dto

import java.math.BigDecimal
import java.time.LocalDateTime

data class PagoClienteResponse(
    val idPago: Int,
    val montoTotal: BigDecimal,
    val metodoPago: String,
    val estado: String,
    val fechaPago: LocalDateTime
)
