package com.Veterinaria.Vetgo.controller

import com.Veterinaria.Vetgo.model.dto.ReporteRequest
import com.Veterinaria.Vetgo.model.dto.ReporteResponse
import com.Veterinaria.Vetgo.service.ReporteService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/reportes")
class ReporteController(
    private val reporteService: ReporteService
) {

    @PostMapping("/crear")
    fun crearReporte(@RequestBody request: ReporteRequest): ReporteResponse =
        reporteService.crearReporte(request)

    @GetMapping("/{id}")
    fun obtenerPorId(@PathVariable id: Int): ReporteResponse =
        reporteService.obtenerPorId(id)

    @GetMapping("/veterinario/{idVeterinario}")
    fun obtenerPorVeterinario(@PathVariable idVeterinario: Int): List<ReporteResponse> =
        reporteService.obtenerPorVeterinario(idVeterinario)

    @GetMapping("/cliente/{idCliente}")
    fun obtenerPorCliente(@PathVariable idCliente: Int): List<ReporteResponse> =
        reporteService.obtenerPorCliente(idCliente)

    @GetMapping("/cita/{idCita}")
    fun obtenerPorCita(@PathVariable idCita: Int): ReporteResponse =
        reporteService.obtenerPorCita(idCita)

    /*@GetMapping("/servicio/{idServicio}")
    fun obtenerPorServicio(@PathVariable idServicio: Int): List<ReporteResponse> =
        reporteService.obtenerPorServicio(idServicio)
     */

    @DeleteMapping("/eliminar/{idReporte}")
    fun eliminarReporte(@PathVariable idReporte: Int) =
        reporteService.eliminarReporte(idReporte)
}
