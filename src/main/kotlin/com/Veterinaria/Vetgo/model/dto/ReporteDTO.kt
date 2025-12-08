package com.Veterinaria.Vetgo.model.dto

import java.math.BigDecimal

data class ReporteRequest(
    val idUsuario: Int,
    val idCliente: Int,
    val idServicio: Int,
    val idMascota: Int,
    val idCita: Int,
    val detalles: String?,
    val observaciones: String?,
    val costeExtra: Double?,
    val evidenciaFoto: String?
)

data class ReporteResponse(
    val idReporte: Int,
    val idVeterinario: Int,
    val idCliente: Int,
    val idServicio: Int,
    val idMascota: Int,
    val idCita: Int,
    val detalles: String?,
    val observaciones: String?,
    val costeExtra: BigDecimal,
    val evidenciaFoto: String?
)
