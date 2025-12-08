package com.Veterinaria.Vetgo.service

import com.Veterinaria.Vetgo.model.dto.MascotaRequest
import com.Veterinaria.Vetgo.model.dto.MascotaResponse
import com.Veterinaria.Vetgo.model.entity.Mascota
import com.Veterinaria.Vetgo.repository.MascotaRepository
import org.springframework.stereotype.Service

@Service
class MascotaService(
    private val mascotaRepository: MascotaRepository
) {

    fun getMascotasUsuario(usuarioId: Int, rolUsuario: String): List<MascotaResponse> {

        if (rolUsuario != "Cliente")
            throw IllegalAccessException("Solo los clientes pueden consultar sus mascotas.")

        return mascotaRepository.findByUsuarioId(usuarioId)
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

    fun registrarMascota(request: MascotaRequest, rolUsuario: String): MascotaResponse {

        if (rolUsuario != "Cliente")
            throw IllegalAccessException("Solo los clientes pueden registrar mascotas.")

        if (request.sexo != null &&
            request.sexo !in listOf("Macho", "Hembra")
        ) {
            throw IllegalArgumentException("El sexo debe ser 'Macho' o 'Hembra'")
        }

        val mascota = Mascota(
            usuarioId = request.usuarioId,
            nombre = request.nombre,
            especie = request.especie,
            edad = request.edad,
            peso = request.peso,
            sexo = request.sexo
        )

        val guardado = mascotaRepository.save(mascota)

        return MascotaResponse(
            idMascota = guardado.idMascota,
            nombre = guardado.nombre,
            especie = guardado.especie,
            edad = guardado.edad,
            peso = guardado.peso,
            sexo = guardado.sexo
        )
    }

    fun eliminarMascota(idMascota: Int, idUsuario: Int, rolUsuario: String) {

        if (rolUsuario != "Cliente")
            throw IllegalAccessException("Solo los clientes pueden eliminar mascotas.")

        val mascota = mascotaRepository.findById(idMascota)
            .orElseThrow { IllegalArgumentException("La mascota no existe.") }

        if (mascota.usuarioId != idUsuario)
            throw IllegalAccessException("No tienes permiso para eliminar esta mascota.")

        mascotaRepository.delete(mascota)
    }
}
