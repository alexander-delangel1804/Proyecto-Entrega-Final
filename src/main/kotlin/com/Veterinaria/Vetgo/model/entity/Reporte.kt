package com.Veterinaria.Vetgo.model.entity

import jakarta.persistence.*
import jakarta.persistence.GenerationType
import java.math.BigDecimal

@Entity
@Table(name = "REPORTES")
data class Reporte(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reporte")
    val idReporte: Int = 0,

    @Column(name = "fk_id_veterinario", nullable = false)
    val idVeterinario: Int,

    @Column(name = "fk_id_cliente", nullable = false)
    val idCliente: Int,

    @Column(name = "fk_servicio", nullable = false)
    val idServicio: Int,

    @Column(name = "fk_mascota", nullable = false)
    val idMascota: Int,

    @Column(name = "fk_id_cita", nullable = false)
    val idCita: Int,

    @Column(columnDefinition = "TEXT")
    val detalles: String? = null,

    @Column(columnDefinition = "TEXT")
    val observaciones: String? = null,

    @Column(precision = 10, scale = 2)
    var costeExtra: BigDecimal = BigDecimal.ZERO,

    @Column(name = "evidencia_foto")
    val evidenciaFoto: String? = null
)


