package com.Veterinaria.Vetgo.model.dto

import java.math.BigDecimal

data class ServicioRequest(
    val nombreServicio: String,
    val descripcion: String?,
    val precioBase: BigDecimal
)

data class ServicioUpdateRequest(
    val nombreServicio: String,
    val descripcion: String?,
    val precioBase: BigDecimal
)
