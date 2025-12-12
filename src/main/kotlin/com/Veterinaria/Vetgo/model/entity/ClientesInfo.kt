package com.Veterinaria.Vetgo.model.entity

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "CLIENTES_INFO")
data class ClientesInfo(

    @Id
    @Column(name = "fk_id_cliente")
    val clienteId: Int = 0,

    @OneToOne
    @MapsId
    @JoinColumn(name = "fk_id_cliente")
    val cliente: Usuario,

    @Column(nullable = false)
    var saldo: BigDecimal = BigDecimal.ZERO,

    @Column(name = "tarjeta_marca")
    var tarjetaMarca: String? = null,

    @Column(name = "tarjeta_ultimos4")
    var tarjetaUltimos4: String? = null,

    @Column(name = "tarjeta_expiracion")
    var tarjetaExpiracion: String? = null,

    @Column(name = "tarjeta_token")
    var tarjetaToken: String? = null,

    @Column(name = "fecha_registro")
    var fechaRegistro: LocalDateTime? = null
)
