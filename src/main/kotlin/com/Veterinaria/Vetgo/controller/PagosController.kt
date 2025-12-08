package com.Veterinaria.Vetgo.controller

import com.Veterinaria.Vetgo.model.dto.PagoClienteResponse
import com.Veterinaria.Vetgo.service.PagoService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
/*
@RestController
@RequestMapping("/pagos")
class PagosController(private val pagoService: PagoService) {

    @PostMapping("/create")
    fun createPago(@RequestBody dto: PagoClienteResponse): ResponseEntity<Any> {
        val pago = pagoService.crearPagoParaCita(dto.citaId, dto.metodoPago)
        return ResponseEntity.ok(mapOf("idPago" to pago.idPago, "monto" to pago.montoTotal))
    }

    @PostMapping("/webhook")
    fun webhook(@RequestBody payload: String, @RequestHeader("Stripe-Signature") sig: String) {
        // validar firma y procesar evento
    }

    @PostMapping("/{id}/completar")
    fun completarPago(@PathVariable id: Int): ResponseEntity<Any> {
        pagoService.marcarPagoCompletado(id)
        return ResponseEntity.ok().build()
    }
}*/

