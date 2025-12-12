package com.Veterinaria.Vetgo.model.entity

import jakarta.persistence.*
import java.math.BigDecimal
@Entity
@Table(name = "SERVICIOS")
data class Servicio(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_servicio")
    val idServicio: Int = 0,

    @Column(name = "nombre_servicio")
    val nombreServicio: String = "",

    @Column(name = "descripcion")
    val descripcion: String? = null,

    @Column(name = "precio_base")
    val precioBase: BigDecimal
)


