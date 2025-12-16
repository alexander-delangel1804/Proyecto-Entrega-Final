package com.Veterinaria.Vetgo.model.entity

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "ENCUESTAS")
data class Encuesta(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_encuesta")
    val idEncuesta: Int = 0,

    @Column(name = "fk_id_cliente", nullable = false)
    val idCliente: Int,

    @Column(name = "fk_id_veterinario", nullable = false)
    val idVeterinario: Int,

    @Column(name = "calificacion", nullable = false)
    val calificacion: Int,

    @Column(name = "comentario")
    val comentario: String? = null,

    @Column(name = "fecha")
    val fecha: Date = Date()
)