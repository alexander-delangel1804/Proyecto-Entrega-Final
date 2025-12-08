package com.Veterinaria.Vetgo.model.entity

import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "PAGOS")
data class Pago(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago")
    val idPago: Int = 0,

    @Column(name = "fk_servicio", nullable = false)
    val fkServicio: Int,

    @Column(name = "fk_coste_extra")
    val fkCosteExtra: Int? = null,

    @Column(name = "monto_total", precision = 10, scale = 2, nullable = false)
    val montoTotal: BigDecimal = BigDecimal("0.00"),

    @Column(name = "metodo_pago", length = 30, nullable = false)
    val metodoPago: String = "Efectivo",

    @Column(name = "estado", length = 20, nullable = false)
    var estado: String = "Pendiente",

    @Column(name = "fecha_pago")
    var fechaPago: java.time.LocalDateTime? = null
)


