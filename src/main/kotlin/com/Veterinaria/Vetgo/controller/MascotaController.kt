package com.Veterinaria.Vetgo.controller

import com.Veterinaria.Vetgo.model.dto.MascotaRequest
import com.Veterinaria.Vetgo.model.dto.MascotaResponse
import com.Veterinaria.Vetgo.service.MascotaService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/mascotas")
class MascotaController(
    private val mascotaService: MascotaService
) {

    @GetMapping("/{usuarioId}")
    fun obtenerMascotas(
        @PathVariable usuarioId: Int,
        @RequestParam rolUsuario: String
    ): List<MascotaResponse> =
        mascotaService.getMascotasUsuario(usuarioId, rolUsuario)


    @PostMapping("/registrar")
    fun registrarMascota(
        @RequestBody request: MascotaRequest,
        @RequestParam rolUsuario: String
    ): MascotaResponse =
        mascotaService.registrarMascota(request, rolUsuario)


    @DeleteMapping("/eliminar/{idMascota}")
    fun eliminarMascota(
        @PathVariable idMascota: Int,
        @RequestParam idUsuario: Int,
        @RequestParam rolUsuario: String
    ): ResponseEntity<String> {

        mascotaService.eliminarMascota(idMascota, idUsuario, rolUsuario)

        return ResponseEntity.ok("Mascota eliminada correctamente.")
    }
}


