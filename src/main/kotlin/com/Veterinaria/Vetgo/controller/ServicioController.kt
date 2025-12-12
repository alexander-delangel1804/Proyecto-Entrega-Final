package com.Veterinaria.Vetgo.controller

import com.Veterinaria.Vetgo.model.dto.ServicioRequest
import com.Veterinaria.Vetgo.model.dto.ServicioUpdateRequest
import com.Veterinaria.Vetgo.model.entity.Servicio
import com.Veterinaria.Vetgo.service.ServicioService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/servicios")
class ServicioController(
    private val servicioService: ServicioService
) {
    @GetMapping("")
    fun getServicios(): ResponseEntity<List<Servicio>> =
        ResponseEntity.ok(servicioService.obtenerServicios())

    @GetMapping("/{id}")
    fun getServicio(@PathVariable id: Int): ResponseEntity<Servicio?> {
        val servicio = servicioService.obtenerServicioPorId(id)
        return if (servicio != null)
            ResponseEntity.ok(servicio)
        else
            ResponseEntity.notFound().build()
    }

    @PostMapping("/crear")
    fun crearServicio(@RequestBody req: ServicioRequest): ResponseEntity<Servicio> {
        val nuevo = servicioService.crearServicio(req)
        return ResponseEntity.ok(nuevo)
    }

    @DeleteMapping("/eliminar/{id}")
    fun eliminarServicio(@PathVariable id: Int): ResponseEntity<String> {
        servicioService.eliminarServicio(id)
        return ResponseEntity.ok("Servicio con id $id eliminado correctamente")
    }

    @PutMapping("/editar/{id}")
    fun actualizarServicio(
        @PathVariable id: Int,
        @RequestBody req: ServicioUpdateRequest
    ): ResponseEntity<Servicio> {
        val actualizado = servicioService.actualizarServicio(id, req)
        return ResponseEntity.ok(actualizado)
    }
}