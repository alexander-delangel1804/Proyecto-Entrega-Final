package com.Veterinaria.Vetgo.service

import com.Veterinaria.Vetgo.exception.citas.*
import com.Veterinaria.Vetgo.model.dto.AccionRequest
import com.Veterinaria.Vetgo.model.dto.CitaResponse
import com.Veterinaria.Vetgo.model.dto.CrearCitaRequest
import com.Veterinaria.Vetgo.model.entity.Cita
import com.Veterinaria.Vetgo.model.enums.CitaAccion
import com.Veterinaria.Vetgo.model.enums.CitaEstado
import com.Veterinaria.Vetgo.repository.CitaRepository
import org.springframework.stereotype.Service
import com.Veterinaria.Vetgo.model.enums.toResponse
import com.Veterinaria.Vetgo.repository.MascotaRepository
import java.text.SimpleDateFormat
import java.util.*

@Service
class CitaService(
    private val citaRepo: CitaRepository,
    private val mascotaRepo: MascotaRepository
) {

    fun obtenerCitasPorCliente(idCliente: Int): List<CitaResponse> {
        return citaRepo.findByFkIdCliente(idCliente)
            .map { it.toResponse() }
    }

    fun ejecutarAccion(idCita: Int, req: AccionRequest): CitaResponse {
        val cita = procesarAccion(
            idCita = idCita,
            accion = req.accion,
            idVeterinario = req.usuario_Id
        )
        return cita.toResponse()
    }

    fun crearCita(req: CrearCitaRequest): Cita {

        val mascota = mascotaRepo.findById(req.fkMascota)
            .orElseThrow {
                RuntimeException("La mascota no existe") // opcional: excepción propia
            }

        if (mascota.clienteId != req.fkIdCliente) {
            throw MascotaNoPerteneceAlClienteException()
        }

        val tieneCitaPendiente = citaRepo.existsByFkIdClienteAndEstado(
            req.fkIdCliente,
            CitaEstado.EN_ESPERA.valor
        )

        if (tieneCitaPendiente) {
            throw ClienteConCitaPendienteException()
        }
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

    fun procesarAccion(
        idCita: Int,
        accion: CitaAccion,
        idVeterinario: Int? = null
    ): Cita {

        val cita = citaRepo.findById(idCita)
            .orElseThrow { CitaNoEncontradaException(idCita) }

        when (accion) {

            CitaAccion.CANCELAR_CLIENTE -> {
                if (cita.estado == CitaEstado.PAGADA.valor) {
                    throw AccionCitaNoPermitidaException(
                        "No puedes cancelar una cita que ya fue pagada"
                    )
                }
                cita.estado = CitaEstado.CANCELADA_CLIENTE.valor
            }

            CitaAccion.CANCELAR_VETERINARIO -> {
                cita.estado = CitaEstado.CANCELADA_VETERINARIO.valor
            }

            CitaAccion.TOMAR -> {
                if (idVeterinario == null) {
                    throw VeterinarioRequeridoException()
                }
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
        }

        return citaRepo.save(cita)
    }

    private fun validarCambio(cita: Cita, requerido: CitaEstado) {
        if (cita.estado != requerido.valor) {
            throw EstadoCitaInvalidoException(
                estadoActual = cita.estado,
                estadoRequerido = requerido.valor
            )
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

        val fechaDate = try {
            formatter.parse(fecha)
        } catch (ex: Exception) {
            throw FormatoFechaInvalidoException()
        }

        return citaRepo.findByFecha(fechaDate)
            .map { it.toResponse() }
    }
}

