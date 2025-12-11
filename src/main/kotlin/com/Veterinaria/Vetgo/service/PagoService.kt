package com.Veterinaria.Vetgo.service

import com.Veterinaria.Vetgo.model.dto.PagoRequest
import com.Veterinaria.Vetgo.model.dto.PagoResponse
import com.Veterinaria.Vetgo.model.dto.PagoUpdateEstadoRequest
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
    private val pagoRepo: PagoRepository
) {

    fun crearPago(req: PagoRequest): PagoResponse {
        val nuevo = Pago(
            fkServicio = req.fkServicio,
            fkCosteExtra = req.fkCosteExtra,
            fkCita = req.fkCita,
            montoTotal = req.montoTotal,
            metodoPago = req.metodoPago,
            estado = "Pendiente",
            fechaPago = LocalDateTime.now()
        )

        return pagoRepo.save(nuevo).toResponse()
    }

    fun obtenerPagoPorId(idPago: Int): PagoResponse {
        val pago = pagoRepo.findByIdPago(idPago)
            ?: throw RuntimeException("No se encontró el pago con id $idPago")
        return pago.toResponse()
    }

    fun obtenerPagosPorServicio(fkServicio: Int): List<PagoResponse> {
        return pagoRepo.findByFkServicio(fkServicio)
            .map { it.toResponse() }
    }

    fun obtenerPagosPorEstado(estado: String): List<PagoResponse> {
        return pagoRepo.findByEstado(estado)
            .map { it.toResponse() }
    }

    fun obtenerPagosPorCita(fkCita: Int): List<PagoResponse> {
        return pagoRepo.findByFkCita(fkCita)
            .map { it.toResponse() }
    }

    fun obtenerPagosPorFecha(fecha: LocalDateTime): List<PagoResponse> {
        return pagoRepo.findByFechaPago(fecha)
            .map { it.toResponse() }
    }

    fun actualizarEstado(idPago: Int, req: PagoUpdateEstadoRequest): PagoResponse {
        val pago = pagoRepo.findByIdPago(idPago)
            ?: throw RuntimeException("No se encontró el pago con id $idPago")

        pago.estado = req.estado
        pago.fechaPago = LocalDateTime.now()

        return pagoRepo.save(pago).toResponse()
    }

    fun Pago.toResponse() = PagoResponse(
        idPago = this.idPago,
        fkServicio = this.fkServicio,
        fkCosteExtra = this.fkCosteExtra,
        fkCita = this.fkCita,
        montoTotal = this.montoTotal,
        metodoPago = this.metodoPago,
        estado = this.estado,
        fechaPago = this.fechaPago
    )


}


