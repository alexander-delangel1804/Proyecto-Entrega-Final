package com.Veterinaria.Vetgo.controller


import com.Veterinaria.Vetgo.model.dto.EncuestaRequest
import com.Veterinaria.Vetgo.model.dto.EncuestaResponse
import com.Veterinaria.Vetgo.service.EncuestaService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/encuestas")
class EncuestaController(private val encuestaService: EncuestaService) {

    @GetMapping
    fun obtenerTodas(): ResponseEntity<List<EncuestaResponse>> =
        ResponseEntity.ok(encuestaService.listarTodas())

    @GetMapping("/id/{id}")
    fun obtenerPorIdEncuesta(@PathVariable id: Int): ResponseEntity<List<EncuestaResponse>> =
        ResponseEntity.ok(encuestaService.obtenerPorIdEncuesta(id))

    @GetMapping("/cliente/{idCliente}")
    fun obtenerPorIdCliente(@PathVariable idCliente: Int): ResponseEntity<List<EncuestaResponse>> =
        ResponseEntity.ok(encuestaService.obtenerPorIdCliente(idCliente))

    @GetMapping("/veterinario/{idVeterinario}")
    fun obtenerPorIdVeterinario(@PathVariable idVeterinario: Int): ResponseEntity<List<EncuestaResponse>> =
        ResponseEntity.ok(encuestaService.obtenerPorIdVeterinario(idVeterinario))

    @GetMapping("/fecha/{fecha}")
    fun obtenerPorFecha(@PathVariable fecha: String): ResponseEntity<List<EncuestaResponse>> =
        ResponseEntity.ok(encuestaService.obtenerPorFecha(fecha))

    @PostMapping
    fun crearEncuesta(@RequestBody request: EncuestaRequest): ResponseEntity<EncuestaResponse> =
        ResponseEntity.ok(encuestaService.crearEncuesta(request))

    @DeleteMapping("/{id}")
    fun eliminar(@PathVariable id: Int): ResponseEntity<String> {
        return if (encuestaService.eliminarEncuesta(id)) {
            ResponseEntity.ok("Encuesta eliminada correctamente.")
        } else {
            ResponseEntity.status(404).body("No se encontró la encuesta.")
        }
    }
}
