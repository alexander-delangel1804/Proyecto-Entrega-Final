package com.Veterinaria.Vetgo.controller

import com.Veterinaria.Vetgo.model.dto.PagoRequest
import com.Veterinaria.Vetgo.model.dto.PagoResponse
import com.Veterinaria.Vetgo.model.dto.PagoUpdateEstadoRequest
import com.Veterinaria.Vetgo.service.PagoService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("/pagos")
class PagoController(
    private val pagoService: PagoService
) {
    @PostMapping
    fun crearPago(@RequestBody req: PagoRequest): ResponseEntity<PagoResponse> {
        return ResponseEntity.ok(pagoService.crearPago(req))
    }

    @GetMapping("/{idPago}")
    fun obtenerPagoPorId(@PathVariable idPago: Int): ResponseEntity<PagoResponse> {
        return ResponseEntity.ok(pagoService.obtenerPagoPorId(idPago))
    }

    @GetMapping("/servicio/{fkServicio}")
    fun obtenerPorServicio(@PathVariable fkServicio: Int): ResponseEntity<List<PagoResponse>> {
        return ResponseEntity.ok(pagoService.obtenerPagosPorServicio(fkServicio))
    }

    @GetMapping("/estado/{estado}")
    fun obtenerPorEstado(@PathVariable estado: String): ResponseEntity<List<PagoResponse>> {
        return ResponseEntity.ok(pagoService.obtenerPagosPorEstado(estado))
    }

    @GetMapping("/cita/{fkCita}")
    fun obtenerPorCita(@PathVariable fkCita: Int): ResponseEntity<List<PagoResponse>> {
        return ResponseEntity.ok(pagoService.obtenerPagosPorCita(fkCita))
    }

    @GetMapping("/fecha")
    fun obtenerPorFecha(@RequestParam fecha: String): ResponseEntity<List<PagoResponse>> {
        val fechaParsed = LocalDateTime.parse(fecha)
        return ResponseEntity.ok(pagoService.obtenerPagosPorFecha(fechaParsed))
    }



    @PutMapping("/{idPago}/estado")
    fun actualizarEstado(
        @PathVariable idPago: Int,
        @RequestBody req: PagoUpdateEstadoRequest
    ): ResponseEntity<PagoResponse> {
        return ResponseEntity.ok(pagoService.actualizarEstado(idPago, req))
    }
}

