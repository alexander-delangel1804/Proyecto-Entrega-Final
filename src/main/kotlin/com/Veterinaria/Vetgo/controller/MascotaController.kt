package com.Veterinaria.Vetgo.controller

import com.Veterinaria.Vetgo.model.dto.MascotaRequest
import com.Veterinaria.Vetgo.model.dto.MascotaResponse
import com.Veterinaria.Vetgo.model.dto.MascotaUpdateRequest
import com.Veterinaria.Vetgo.service.MascotaService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/mascotas")
class MascotaController(
    private val mascotaService: MascotaService
) {
    @GetMapping("/{clienteId}")
    fun obtenerMascotas(@RequestParam usuarioId: Int): List<MascotaResponse> =
        mascotaService.getMascotasUsuario(usuarioId)

    @PostMapping("/registrar")
    fun registrarMascota(@RequestBody request: MascotaRequest): ResponseEntity<MascotaResponse> {
        val mascota = mascotaService.registrarMascota(request)
        return ResponseEntity.ok(mascota)
    }

    @DeleteMapping("/eliminar/{idMascota}")
    fun eliminarMascota(@PathVariable idMascota: Int, @RequestParam idCliente: Int): ResponseEntity<String> {
        mascotaService.eliminarMascota(idMascota, idCliente)
        return ResponseEntity.ok("Mascota eliminada correctamente.")
    }
    @PutMapping("/editar/{idMascota}")
    fun editarMascota(@PathVariable idMascota: Int, @RequestBody req: MascotaUpdateRequest): ResponseEntity<MascotaResponse> {
        val respuesta = mascotaService.editarMascota(idMascota, req)
        return ResponseEntity.ok(respuesta)
    }
}

