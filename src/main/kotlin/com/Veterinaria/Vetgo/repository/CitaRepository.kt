package com.Veterinaria.Vetgo.repository

import com.Veterinaria.Vetgo.model.entity.Cita
import com.Veterinaria.Vetgo.model.enums.CitaEstado
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface CitaRepository : JpaRepository<Cita, Int> {

    fun findByFkIdCliente(fkIdCliente: Int): List<Cita>

    fun findByFkIdVeterinario(fkIdVeterinario: Int): List<Cita>

    fun findByEstado(estado: CitaEstado): List<Cita>

}
