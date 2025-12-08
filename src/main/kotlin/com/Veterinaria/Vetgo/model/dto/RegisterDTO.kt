package com.Veterinaria.Vetgo.model.dto

data class RegisterUsuarioRequest(
    val nombre: String,
    val correo: String,
    val contrasena: String,
    val telefono: String?,
    val direccion: String?,
    val ciudad: String?,
    val rol: String,

    //para veterinario
    val cedulaProfesional: String? = null,
    val especialidad: String? = null
)

data class RegisterUsuarioResponse(
    val idUsuario: Int,
    val mensaje: String
)
