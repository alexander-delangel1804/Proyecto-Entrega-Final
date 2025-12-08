package com.Veterinaria.Vetgo.service

import com.Veterinaria.Vetgo.model.entity.Servicio
import com.Veterinaria.Vetgo.repository.ServicioRepository
import org.springframework.stereotype.Service

@Service
class ServicioService(
    private val repo: ServicioRepository
) {

    fun obtenerServicios(): List<Servicio> = repo.findAll()

    fun obtenerServicioPorId(id: Int): Servicio =
        repo.findById(id).orElseThrow {
            IllegalArgumentException("El servicio con id $id no existe")
        }

}

