package com.Veterinaria.Vetgo.service

import com.Veterinaria.Vetgo.model.dto.ReporteRequest
import com.Veterinaria.Vetgo.model.dto.ReporteResponse
import com.Veterinaria.Vetgo.model.entity.Reporte
import com.Veterinaria.Vetgo.repository.CitaRepository
import com.Veterinaria.Vetgo.repository.ReporteRepository
import com.Veterinaria.Vetgo.repository.UsuarioRepository
import org.springframework.stereotype.Service

@Service
class ReporteService(
    private val reporteRepository: ReporteRepository,
    private val usuarioRepository: UsuarioRepository
) {

    fun crearReporte(request: ReporteRequest): ReporteResponse {

        val usuario = usuarioRepository.findById(request.idUsuario)
            .orElseThrow { IllegalArgumentException("El usuario no existe.") }

        if (usuario.rol != "Veterinario") {
            throw IllegalAccessException("Solo los veterinarios pueden crear reportes.")
        }

        val reporte = Reporte(
            idVeterinario = request.idUsuario,
            idCliente = request.idCliente,
            idServicio = request.idServicio,
            idMascota = request.idMascota,
            idCita = request.idCita,
            detalles = request.detalles,
            observaciones = request.observaciones,
            costeExtra = (request.costeExtra ?: 0.0).toBigDecimal(),
            evidenciaFoto = request.evidenciaFoto
        )

        val guardado = reporteRepository.save(reporte)

        return ReporteResponse(
            idReporte = guardado.idReporte,
            idVeterinario = guardado.idVeterinario,
            idCliente = guardado.idCliente,
            idServicio = guardado.idServicio,
            idMascota = guardado.idMascota,
            idCita = guardado.idCita,
            detalles = guardado.detalles,
            observaciones = guardado.observaciones,
            costeExtra = guardado.costeExtra,
            evidenciaFoto = guardado.evidenciaFoto
        )
    }
}

