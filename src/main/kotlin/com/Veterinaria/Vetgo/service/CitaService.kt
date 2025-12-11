package com.Veterinaria.Vetgo.service

import com.Veterinaria.Vetgo.model.dto.AccionRequest
import com.Veterinaria.Vetgo.model.dto.CitaResponse
import com.Veterinaria.Vetgo.model.dto.CrearCitaRequest
import com.Veterinaria.Vetgo.model.entity.Cita
import com.Veterinaria.Vetgo.model.enums.CitaAccion
import com.Veterinaria.Vetgo.model.enums.CitaEstado
import com.Veterinaria.Vetgo.repository.CitaRepository
import org.springframework.stereotype.Service
import com.Veterinaria.Vetgo.model.enums.toResponse
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

@Service
class CitaService(
    private val citaRepo: CitaRepository
) {
    fun obtenerCitasPorCliente(idCliente: Int): List<CitaResponse> {
        return citaRepo.findByFkIdCliente(idCliente)
            .map { it.toResponse() }
    }

    /*fun obtenerCitasPendientes(): List<CitaResponse> {
        return citaRepo.findByEstado(CitaEstado.EN_ESPERA.valor)
            .map { it.toResponse() }
    }
     */

    fun ejecutarAccion(idCita: Int, req: AccionRequest): CitaResponse {
        val cita = procesarAccion(
            idCita = idCita,
            accion = req.accion,
            idVeterinario = req.actorId
        )
        return cita.toResponse()
    }

    fun crearCita(req: CrearCitaRequest): Cita {
        val nueva = Cita(
            fkIdCliente = req.fkIdCliente,
            fkServicio = req.fkServicio,
            fkMascota = req.fkMascota,
            fechaCita = req.fechaCita,
            metodoPago = req.metodoPago,
            estado = CitaEstado.EN_ESPERA.valor,
            detallesCita = req.detallesCita
        )
        return citaRepo.save(nueva)
    }

    fun procesarAccion(idCita: Int, accion: CitaAccion, idVeterinario: Int? = null): Cita {
        val cita = citaRepo.findById(idCita).orElseThrow {
            RuntimeException("La cita no existe")
        }

        when (accion) {

            CitaAccion.CANCELAR_CLIENTE -> {
                if (cita.estado == CitaEstado.PAGADA.valor)
                    throw RuntimeException("No puedes cancelar una cita pagada")

                cita.estado = CitaEstado.CANCELADA_CLIENTE.valor
            }

            CitaAccion.CANCELAR_VETERINARIO -> {
                cita.estado = CitaEstado.CANCELADA_VETERINARIO.valor
            }

            CitaAccion.TOMAR -> {
                if (idVeterinario == null)
                    throw RuntimeException("Se requiere ID de veterinario")

                cita.fkIdVeterinario = idVeterinario
                cita.estado = CitaEstado.ASIGNADA_VETERINARIO.valor
            }

            CitaAccion.MARCAR_EN_CAMINO -> {
                validarCambio(cita, CitaEstado.ASIGNADA_VETERINARIO)
                cita.estado = CitaEstado.EN_CAMINO.valor
            }

            CitaAccion.INICIAR_SERVICIO -> {
                validarCambio(cita, CitaEstado.EN_CAMINO)
                cita.estado = CitaEstado.EN_SERVICIO.valor
            }

            CitaAccion.COMPLETAR_SERVICIO -> {
                validarCambio(cita, CitaEstado.EN_SERVICIO)
                cita.estado = CitaEstado.COMPLETADA.valor
            }

            CitaAccion.MARCAR_PAGADA -> {
                validarCambio(cita, CitaEstado.COMPLETADA)
                cita.estado = CitaEstado.PAGADA.valor
            }

            else -> {}
        }

        return citaRepo.save(cita)
    }

    private fun validarCambio(cita: Cita, requerido: CitaEstado) {
        if (cita.estado != requerido.valor) {
            throw RuntimeException("La cita debe estar en '${requerido.valor}' para continuar. Estado actual: ${cita.estado}")
        }
    }

    fun obtenerPorMetodoPago(metodoPago: String): List<CitaResponse> {
        return citaRepo.findByMetodoPago(metodoPago)
            .map { it.toResponse() }
    }

    fun obtenerPorEstado(estado: CitaEstado): List<CitaResponse> {
        return citaRepo.findByEstado(estado.valor)
            .map { it.toResponse() }
    }

    fun obtenerPorFechaString(fecha: String): List<CitaResponse> {
        val formatter = SimpleDateFormat("yyyy-MM-dd")
        formatter.timeZone = TimeZone.getDefault()

        val fechaDate = formatter.parse(fecha)
            ?: throw RuntimeException("Formato inválido (usar YYYY-MM-DD)")

        return citaRepo.findByFecha(fechaDate)
            .map { it.toResponse() }
    }


}

