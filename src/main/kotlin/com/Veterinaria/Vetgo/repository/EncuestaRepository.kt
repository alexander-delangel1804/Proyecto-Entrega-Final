package com.Veterinaria.Vetgo.repository

import com.Veterinaria.Vetgo.model.entity.Encuesta
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface EncuestaRepository : JpaRepository<Encuesta, Int> {

    fun findByIdEncuesta(idEncuesta: Int): List<Encuesta>

    fun findByIdCliente(idCliente: Int): List<Encuesta>

    fun findByIdVeterinario(idVeterinario: Int): List<Encuesta>

    fun findByFecha(fecha: Date): List<Encuesta>

    fun existsByIdClienteAndIdVeterinario(idCliente: Int, idVeterinario: Int): Boolean

}
