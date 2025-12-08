package com.Veterinaria.Vetgo.model.dto

data class LoginRequest(
    val correo: String,
    val contrasena: String
)
data class LoginResponse(
    val mensaje: String,
    val usuario: UsuarioResponse?
)
