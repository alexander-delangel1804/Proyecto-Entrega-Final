package com.Veterinaria.Vetgo.controller

import com.Veterinaria.Vetgo.model.entity.Servicio
import com.Veterinaria.Vetgo.service.ServicioService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/servicios")
class ServicioController(
    private val service: ServicioService
) {
    @GetMapping("")
    fun getServicios(): ResponseEntity<List<Servicio>> =
        ResponseEntity.ok(service.obtenerServicios())

    @GetMapping("/{id}")
    fun getServicio(@PathVariable id: Int): ResponseEntity<Servicio?> {
        val servicio = service.obtenerServicioPorId(id)
        return if (servicio != null)
            ResponseEntity.ok(servicio)
        else
            ResponseEntity.notFound().build()
    }
}