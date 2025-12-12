package com.Veterinaria.Vetgo.service

import com.Veterinaria.Vetgo.model.dto.MascotaRequest
import com.Veterinaria.Vetgo.model.dto.MascotaResponse
import com.Veterinaria.Vetgo.model.dto.MascotaUpdateRequest
import com.Veterinaria.Vetgo.model.entity.Mascota
import com.Veterinaria.Vetgo.repository.MascotaRepository
import org.springframework.stereotype.Service

@Service
class MascotaService(
    private val mascotaRepo: MascotaRepository
) {
    fun getMascotasUsuario(clienteId: Int): List<MascotaResponse> {

        return mascotaRepo.findByClienteId(clienteId)
            .map {
                MascotaResponse(
                    idMascota = it.idMascota,
                    nombre = it.nombre,
                    especie = it.especie,
                    edad = it.edad,
                    peso = it.peso,
                    sexo = it.sexo
                )
            }
    }

    fun registrarMascota(request: MascotaRequest): MascotaResponse {

        if (request.sexo != null &&
            request.sexo !in listOf("Macho", "Hembra")
        ) {
            throw IllegalArgumentException("El sexo debe ser 'Macho' o 'Hembra'")
        }

        val mascota = Mascota(
            clienteId = request.clienteId,
            nombre = request.nombre,
            especie = request.especie,
            edad = request.edad,
            peso = request.peso,
            sexo = request.sexo
        )

        val guardado = mascotaRepo.save(mascota)

        return MascotaResponse(
            idMascota = guardado.idMascota,
            nombre = guardado.nombre,
            especie = guardado.especie,
            edad = guardado.edad,
            peso = guardado.peso,
            sexo = guardado.sexo
        )
    }

    fun eliminarMascota(idMascota: Int, idUsuario: Int) {

        val mascota = mascotaRepo.findById(idMascota)
            .orElseThrow { IllegalArgumentException("La mascota no existe.") }

        if (mascota.clienteId != idUsuario)
            throw IllegalAccessException("No tienes permiso para eliminar esta mascota.")

        mascotaRepo.delete(mascota)
    }

    fun editarMascota(idMascota: Int, req: MascotaUpdateRequest): MascotaResponse {
        val mascota = mascotaRepo.findById(idMascota)
            .orElseThrow { RuntimeException("Mascota no encontrada") }

        val mascotaEditada = mascota.copy(
            nombre = req.nombre ?: mascota.nombre,
            especie = req.especie ?: mascota.especie,
            edad = req.edad ?: mascota.edad,
            peso = req.peso ?: mascota.peso,
            sexo = req.sexo ?: mascota.sexo
        )

        val guardada = mascotaRepo.save(mascotaEditada)
        return guardada.toResponse()
    }

    fun Mascota.toResponse() = MascotaResponse(
        idMascota = idMascota,
        nombre = nombre,
        especie = especie,
        edad = edad,
        peso = peso,
        sexo = sexo
    )
}
