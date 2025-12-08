package com.Veterinaria.Vetgo.model.dto

data class UsuarioResponse(
    val idUsuario: Int,
    val nombre: String,
    val correo: String,
    val telefono: String?,
    val direccion: String?,
    val ciudad: String?,
    val rol: String
)