package com.Veterinaria.Vetgo.controller

import com.Veterinaria.Vetgo.model.dto.LoginRequest
import com.Veterinaria.Vetgo.model.dto.LoginResponse
import com.Veterinaria.Vetgo.model.entity.Usuario
import com.Veterinaria.Vetgo.service.UsuarioService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/login")
class LoginController(
    private val usuarioService: UsuarioService
) {
    @PostMapping("")
    fun login(@RequestBody request: LoginRequest): LoginResponse {
        return usuarioService.login(request)
    }
}


