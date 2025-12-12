package com.Veterinaria.Vetgo.model.dto

data class RegisterUsuarioResponse(
    val idUsuario: Int,
    val mensaje: String
)

//Clientes
data class RegisterClienteRequest(
    val nombre: String,
    val correo: String,
    val contrasena: String,
    val telefono: String?,
    val direccion: String?,
    val ciudad: String?
)



//Veterinarios

data class RegisterVeterinarioRequest(
    val nombre: String,
    val correo: String,
    val contrasena: String,
    val telefono: String?,
    val direccion: String?,
    val ciudad: String?,
    val cedulaProfesional: String,
    val especialidad: String
)
