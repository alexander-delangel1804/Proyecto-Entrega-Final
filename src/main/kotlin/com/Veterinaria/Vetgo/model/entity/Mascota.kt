package com.Veterinaria.Vetgo.model.entity

import jakarta.persistence.*

@Entity
@Table(name = "MASCOTAS")
data class Mascota(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mascota")
    val idMascota: Int = 0,

    @Column(name = "fk_id_cliente", nullable = false)
    val clienteId: Int,

    @Column(nullable = false)
    val nombre: String,

    @Column(nullable = false)
    val especie: String,

    @Column(nullable = false)
    val edad: Int?,

    @Column(nullable = false)
    val peso: Float?,

    @Column(nullable = false)
    val sexo: String
)
