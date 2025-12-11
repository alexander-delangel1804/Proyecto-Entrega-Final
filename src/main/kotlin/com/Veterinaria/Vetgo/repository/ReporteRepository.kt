package com.Veterinaria.Vetgo.repository

import com.Veterinaria.Vetgo.model.entity.Reporte
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

interface ReporteRepository : JpaRepository<Reporte, Int> {
    fun findByIdCita(idCita: Int): Reporte?
    fun findByIdVeterinario(idVeterinario: Int): List<Reporte>
    fun findByIdCliente(idCliente: Int): List<Reporte>
    //fun findByIdServicio(idServicio: Int): List<Reporte>
}
