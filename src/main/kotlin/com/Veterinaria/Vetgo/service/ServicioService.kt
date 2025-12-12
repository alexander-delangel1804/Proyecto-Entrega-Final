package com.Veterinaria.Vetgo.service

import com.Veterinaria.Vetgo.model.dto.ServicioRequest
import com.Veterinaria.Vetgo.model.dto.ServicioUpdateRequest
import com.Veterinaria.Vetgo.model.entity.Servicio
import com.Veterinaria.Vetgo.repository.ServicioRepository
import org.springframework.stereotype.Service

@Service
class ServicioService(
    private val servicioRepo: ServicioRepository
) {

    fun obtenerServicios(): List<Servicio> = servicioRepo.findAll()

    fun obtenerServicioPorId(id: Int): Servicio =
        servicioRepo.findById(id).orElseThrow {
            IllegalArgumentException("El servicio con id $id no existe")
        }

    fun crearServicio(req: ServicioRequest): Servicio {
        val servicio = Servicio(
            nombreServicio = req.nombreServicio,
            descripcion = req.descripcion,
            precioBase = req.precioBase
        )
        return servicioRepo.save(servicio)
    }
    fun eliminarServicio(idServicio: Int) {
        if (!servicioRepo.existsById(idServicio)) {
            throw RuntimeException("El servicio con ID $idServicio no existe")
        }
        servicioRepo.deleteById(idServicio)
    }

    fun actualizarServicio(idServicio: Int, req: ServicioUpdateRequest): Servicio {
        val servicio = servicioRepo.findById(idServicio)
            .orElseThrow { RuntimeException("El servicio con ID $idServicio no existe") }

        val actualizado = servicio.copy(
            nombreServicio = req.nombreServicio,
            descripcion = req.descripcion,
            precioBase = req.precioBase
        )

        return servicioRepo.save(actualizado)
    }

}

