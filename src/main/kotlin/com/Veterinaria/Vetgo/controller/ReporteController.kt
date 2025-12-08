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
    fun crearReporte(
        @RequestBody request: ReporteRequest
    ): ReporteResponse {
        return reporteService.crearReporte(request)
    }
}
