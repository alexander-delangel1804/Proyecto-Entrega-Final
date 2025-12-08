package com.Veterinaria.Vetgo.model.entity

import com.Veterinaria.Vetgo.model.enums.CitaEstado
import jakarta.persistence.*
import java.time.LocalDateTime
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "CITAS")
data class Cita(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cita")
    val idCita: Int = 0,

    @Column(name = "fk_id_cliente", nullable = false)
    var fkIdCliente: Int,

    @Column(name = "fk_servicio", nullable = false)
    var fkServicio: Int,

    @Column(name = "fk_mascota", nullable = false)
    var fkMascota: Int,

    @Column(name = "fk_id_veterinario")
    var fkIdVeterinario: Int? = null,

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_cita", nullable = false)
    val fechaCita: Date,

    @Column(name = "estado", nullable = false, length = 50)
    var estado: String = CitaEstado.EN_ESPERA.valor,

    @Column(name = "detalles_cita")
    var detallesCita: String? = null,

    @Column(name = "metodo_pago", nullable = false)
    var metodoPago: String
)
