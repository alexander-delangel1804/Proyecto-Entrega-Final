package com.Veterinaria.Vetgo.model.dto

data class MascotaRequest(
    val clienteId: Int,
    val nombre: String,
    val especie: String,
    val edad: Int?,
    val peso: Float?,
    val sexo: String
)

data class MascotaResponse(
    val idMascota: Int,
    val nombre: String,
    val especie: String,
    val edad: Int?,
    val peso: Float?,
    val sexo: String
)

data class MascotaUpdateRequest(
    val nombre: String?,
    val especie: String?,
    val edad: Int?,
    val peso: Float?,
    val sexo: String?
)
