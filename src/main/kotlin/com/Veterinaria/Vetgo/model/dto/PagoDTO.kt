package com.Veterinaria.Vetgo.model.dto

import com.Veterinaria.Vetgo.model.entity.Pago
import java.math.BigDecimal
import java.time.LocalDateTime

data class PagoRequest(
    val fkServicio: Int,
    val fkCosteExtra: Int? = null,
    val fkCita: Int,
    val montoTotal: BigDecimal,
    val metodoPago: String
)

data class PagoUpdateEstadoRequest(
    val estado: String
)

data class PagoResponse(
    val idPago: Int,
    val fkServicio: Int,
    val fkCosteExtra: Int?,
    val fkCita: Int,
    val montoTotal: BigDecimal,
    val metodoPago: String,
    val estado: String,
    val fechaPago: LocalDateTime?
)


