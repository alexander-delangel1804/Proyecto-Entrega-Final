package com.Veterinaria.Vetgo.model.entity

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "VETERINARIOS_INFO")
data class VeterinariosInfo(
    @Id
    @Column(name = "fk_id_veterinario")
    val idVeterinario: Int = 0,

    @OneToOne
    @MapsId
    @JoinColumn(name = "fk_id_veterinario")
    val usuario: Usuario,

    @Column(name = "cedula_profesional", nullable = false, length = 50)
    var cedulaProfesional: String,

    @Column(name = "especialidad", length = 50)
    var especialidad: String? = null,

    @Column(name = "estado", length = 20)
    var estado: String = "Inactivo",

    @Column(name = "calif_promedio", precision = 3, scale = 2)
    var califPromedio: BigDecimal = BigDecimal.ZERO

)
