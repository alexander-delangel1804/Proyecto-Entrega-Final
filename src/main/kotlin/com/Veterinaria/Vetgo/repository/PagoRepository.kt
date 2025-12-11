package com.Veterinaria.Vetgo.repository

import com.Veterinaria.Vetgo.model.entity.Pago
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface PagoRepository : JpaRepository<Pago, Int> {
    fun findByIdPago(idPago: Int): Pago?

    fun findByFkServicio(fkServicio: Int): List<Pago>

    fun findByEstado(estado: String): List<Pago>

    fun findByFkCita(fkCita: Int): List<Pago>

    fun findByFechaPago(fechaPago: LocalDateTime): List<Pago>

}
