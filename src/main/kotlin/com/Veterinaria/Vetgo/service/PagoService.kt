package com.Veterinaria.Vetgo.service

import com.Veterinaria.Vetgo.model.entity.Pago
import com.Veterinaria.Vetgo.repository.CitaRepository
import com.Veterinaria.Vetgo.repository.PagoRepository
import com.Veterinaria.Vetgo.repository.ReporteRepository
import com.Veterinaria.Vetgo.repository.ServicioRepository
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.LocalDateTime

@Service
class PagoService(
    private val pagoRepository: PagoRepository,
    private val citaRepository: CitaRepository,
    private val servicioRepository: ServicioRepository,
    private val reporteRepository: ReporteRepository,
) {

    fun crearPagoParaCita(citaId: Int, metodo: String): Pago {

        val cita = citaRepository.findById(citaId)
            .orElseThrow { RuntimeException("Cita no encontrada") }

        val servicio = servicioRepository.findById(cita.fkServicio)
            .orElseThrow { RuntimeException("Servicio no encontrado") }

        val reporte = reporteRepository.findByIdCita(citaId)

        val costeExtra = reporte?.costeExtra ?: 0.0

        val monto = servicio.precioBase
            .add(BigDecimal.ZERO)

        val pago = Pago(
            fkServicio = cita.fkServicio,
            fkCosteExtra = reporte?.idReporte,
            montoTotal = monto,
            metodoPago = metodo,
            estado = "Pendiente",
            fechaPago = null
        )

        return pagoRepository.save(pago)
    }







    fun marcarPagoCompletado(idPago: Int) {

        val pago = pagoRepository.findById(idPago)
            .orElseThrow { RuntimeException("Pago no encontrado") }

        pago.estado = "Completado"
        pago.fechaPago = LocalDateTime.now()

        pagoRepository.save(pago)
    }
}

