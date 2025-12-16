package com.Veterinaria.Vetgo.repository

import com.Veterinaria.Vetgo.model.entity.Cita
import com.Veterinaria.Vetgo.model.enums.CitaEstado
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime
import java.util.*

@Repository
interface CitaRepository : JpaRepository<Cita, Int> {

    fun findByFkIdCliente(fkIdCliente: Int): List<Cita>

    fun findByFkIdVeterinario(fkIdVeterinario: Int): List<Cita>

    //fun findByFechaCita(fechaCita: Date): List<Cita>

    @Query("SELECT c FROM Cita c WHERE FUNCTION('DATE', c.fechaCita) = :fecha")
    fun findByFecha(fecha: Date): List<Cita>
    fun findByEstado(estado: String): List<Cita>

    fun findByMetodoPago(metodoPago: String): List<Cita>

    fun existsByFkIdClienteAndEstado(idCliente: Int, estado: String): Boolean



}
