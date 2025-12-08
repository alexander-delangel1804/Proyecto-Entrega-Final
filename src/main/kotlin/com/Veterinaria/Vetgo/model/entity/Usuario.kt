package com.Veterinaria.Vetgo.model.entity

import jakarta.persistence.*
@Entity
@Table(name = "USUARIOS")
data class Usuario(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    val idUsuario: Int = 0,

    @Column(name = "nombre", nullable = false, length = 50)
    var nombre: String,

    @Column(name = "correo", nullable = false, length = 50, unique = true)
    var correo: String,

    @Column(name = "contrasena", nullable = false, length = 50)
    var contrasena: String,

    @Column(name = "telefono", length = 10)
    var telefono: String? = null,

    @Column(name = "direccion", length = 100)
    var direccion: String? = null,

    @Column(name = "ciudad", length = 20)
    var ciudad: String? = null,

    @Column(name = "rol", length = 20)
    var rol: String
)

