package com.Veterinaria.Vetgo.model.dto

import java.util.*

data class EncuestaRequest(
    val idCliente: Int,
    val idVeterinario: Int,
    val calificacion: Int,
    val comentario: String?
)

data class EncuestaResponse(
    val idEncuesta: Int,
    val idCliente: Int,
    val idVeterinario: Int,
    val calificacion: Int,
    val comentario: String?,
    val fecha: Date
)