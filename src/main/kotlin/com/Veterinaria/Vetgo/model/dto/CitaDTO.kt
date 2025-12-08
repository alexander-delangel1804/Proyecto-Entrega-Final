package com.Veterinaria.Vetgo.model.dto

import com.Veterinaria.Vetgo.model.enums.CitaAccion
import com.Veterinaria.Vetgo.model.enums.CitaEstado
import java.time.LocalDateTime
import java.util.*

data class CrearCitaRequest(
    val fkIdCliente: Int,
    val fkServicio: Int,
    val fkMascota: Int,
    val fechaCita: Date,
    val detallesCita: String?,
    val metodoPago: String
)

data class CitaResponse(
    val idCita: Int,
    val fkIdCliente: Int,
    val fkServicio: Int,
    val fkMascota: Int,
    val fkIdVeterinario: Int?,
    val fechaCita: LocalDateTime,
    val estado: CitaEstado,
    val detallesCita: String?,
    val metodoPago: String
)

data class AccionRequest(
    val accion: CitaAccion,
    val actorId: Int
)
/*
data class CitaListaResponse(
    val citas: List<CitaResponse>
)

data class CancelarCitaRequest(
    val motivo: String? = null
)*/



