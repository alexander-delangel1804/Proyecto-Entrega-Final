package com.Veterinaria.Vetgo.controller

import com.Veterinaria.Vetgo.model.dto.AccionRequest
import com.Veterinaria.Vetgo.model.dto.CitaResponse
import com.Veterinaria.Vetgo.model.dto.CrearCitaRequest
import com.Veterinaria.Vetgo.model.enums.toResponse
import com.Veterinaria.Vetgo.service.CitaService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/citas")
class CitaController(private val citaService: CitaService) {

    @PostMapping("/crearCita")
    fun crearCita(@RequestBody req: CrearCitaRequest): ResponseEntity<CitaResponse> {
        val creada = citaService.crearCita(req)
        return ResponseEntity.ok(creada.toResponse())
    }

    @GetMapping("/historial/{idCliente}")
    fun getCitasCliente(@PathVariable idCliente: Int): ResponseEntity<List<CitaResponse>> {
        val list = citaService.obtenerCitasPorCliente(idCliente)
        return ResponseEntity.ok(list)
    }

    @GetMapping("/pendientes")
    fun getPendientes(): ResponseEntity<List<CitaResponse>> {
        val list = citaService.obtenerCitasPendientes()
        return ResponseEntity.ok(list)
    }

    @PutMapping("/accion/{idCita}")
    fun accionSobreCita(
        @PathVariable idCita: Int,
        @RequestBody req: AccionRequest
    ): ResponseEntity<CitaResponse> {

        val res = citaService.ejecutarAccion(idCita, req)
        return ResponseEntity.ok(res)
    }
}
