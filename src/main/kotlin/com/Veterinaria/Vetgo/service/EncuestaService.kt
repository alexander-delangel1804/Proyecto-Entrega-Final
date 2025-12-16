package com.Veterinaria.Vetgo.service

import com.Veterinaria.Vetgo.exception.encuestas.*
import com.Veterinaria.Vetgo.model.dto.EncuestaRequest
import com.Veterinaria.Vetgo.model.dto.EncuestaResponse
import com.Veterinaria.Vetgo.model.entity.Encuesta
import com.Veterinaria.Vetgo.repository.EncuestaRepository
import org.springframework.stereotype.Service
import java.text.SimpleDateFormat

@Service
class EncuestaService(
    private val encuestaRepository: EncuestaRepository
) {

    fun listarTodas(): List<EncuestaResponse> =
        encuestaRepository.findAll().map { it.toDTO() }

    fun obtenerPorIdEncuesta(id: Int): List<EncuestaResponse> {
        val encuestas = encuestaRepository.findByIdEncuesta(id)
        if (encuestas.isEmpty()) {
            throw EncuestaNoEncontradaException(id)
        }
        return encuestas.map { it.toDTO() }
    }

    fun obtenerPorIdCliente(idCliente: Int): List<EncuestaResponse> =
        encuestaRepository.findByIdCliente(idCliente).map { it.toDTO() }

    fun obtenerPorIdVeterinario(idVeterinario: Int): List<EncuestaResponse> =
        encuestaRepository.findByIdVeterinario(idVeterinario).map { it.toDTO() }

    fun obtenerPorFecha(fechaStr: String): List<EncuestaResponse> {
        val formato = SimpleDateFormat("yyyy-MM-dd")

        val fecha = try {
            formato.parse(fechaStr)
        } catch (ex: Exception) {
            throw FormatoFechaEncuestaInvalidoException()
        }

        return encuestaRepository.findByFecha(fecha).map { it.toDTO() }
    }

    fun crearEncuesta(request: EncuestaRequest): EncuestaResponse {

        if (request.calificacion !in 1..5) {
            throw CalificacionFueraDeRangoException()
        }

        if (request.idCliente == request.idVeterinario) {
            throw VeterinarioNoPuedeCrearEncuestaException()
        }

        if (request.idVeterinario <= 0) {
            throw ClienteNoPuedeCalificarClienteException()
        }

        if (
            encuestaRepository.existsByIdClienteAndIdVeterinario(
                request.idCliente,
                request.idVeterinario
            )
        ) {
            throw EncuestaDuplicadaException()
        }

        val nuevaEncuesta = Encuesta(
            idCliente = request.idCliente,
            idVeterinario = request.idVeterinario,
            calificacion = request.calificacion,
            comentario = request.comentario
        )

        return encuestaRepository.save(nuevaEncuesta).toDTO()
    }

    fun eliminarEncuesta(id: Int) {
        if (!encuestaRepository.existsById(id)) {
            throw EncuestaNoEncontradaException(id)
        }
        encuestaRepository.deleteById(id)
    }

    private fun Encuesta.toDTO(): EncuestaResponse =
        EncuestaResponse(
            idEncuesta = idEncuesta,
            idCliente = idCliente,
            idVeterinario = idVeterinario,
            calificacion = calificacion,
            comentario = comentario,
            fecha = fecha
        )



}

