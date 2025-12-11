package com.Veterinaria.Vetgo.service

import com.Veterinaria.Vetgo.model.dto.EncuestaRequest
import com.Veterinaria.Vetgo.model.dto.EncuestaResponse
import com.Veterinaria.Vetgo.model.entity.Encuesta
import com.Veterinaria.Vetgo.repository.EncuestaRepository
import org.springframework.stereotype.Service
import java.text.SimpleDateFormat
import java.util.*

@Service
class EncuestaService(private val encuestaRepository: EncuestaRepository) {

    fun listarTodas(): List<EncuestaResponse> =
        encuestaRepository.findAll().map { it.toDTO() }

    fun obtenerPorIdEncuesta(id: Int): List<EncuestaResponse> =
        encuestaRepository.findByIdEncuesta(id).map { it.toDTO() }

    fun obtenerPorIdCliente(idCliente: Int): List<EncuestaResponse> =
        encuestaRepository.findByIdCliente(idCliente).map { it.toDTO() }

    fun obtenerPorIdVeterinario(idVeterinario: Int): List<EncuestaResponse> =
        encuestaRepository.findByIdVeterinario(idVeterinario).map { it.toDTO() }

    fun obtenerPorFecha(fechaStr: String): List<EncuestaResponse> {
        val formato = SimpleDateFormat("yyyy-MM-dd")
        val fecha: Date = formato.parse(fechaStr)
        return encuestaRepository.findByFecha(fecha).map { it.toDTO() }
    }

    fun crearEncuesta(request: EncuestaRequest): EncuestaResponse {
        val nuevaEncuesta = Encuesta(
            idCliente = request.idCliente,
            idVeterinario = request.idVeterinario,
            calificacion = request.calificacion,
            comentario = request.comentario
        )

        return encuestaRepository.save(nuevaEncuesta).toDTO()
    }

    fun eliminarEncuesta(id: Int): Boolean {
        return if (encuestaRepository.existsById(id)) {
            encuestaRepository.deleteById(id)
            true
        } else false
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
