package com.Veterinaria.Vetgo.controller

import com.Veterinaria.Vetgo.model.dto.RegisterUsuarioRequest
import com.Veterinaria.Vetgo.model.dto.RegisterUsuarioResponse
import com.Veterinaria.Vetgo.service.RegisterService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/register")
class RegisterController(
    private val registroService: RegisterService
) {

    @PostMapping("")
    fun registrar(@RequestBody req: RegisterUsuarioRequest): RegisterUsuarioResponse {
        return registroService.registrarUsuario(req)
    }
}