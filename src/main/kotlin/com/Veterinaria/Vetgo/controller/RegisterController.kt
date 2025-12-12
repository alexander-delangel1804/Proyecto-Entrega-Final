package com.Veterinaria.Vetgo.controller

import com.Veterinaria.Vetgo.model.dto.RegisterClienteRequest
import com.Veterinaria.Vetgo.model.dto.RegisterUsuarioResponse
import com.Veterinaria.Vetgo.model.dto.RegisterVeterinarioRequest
import com.Veterinaria.Vetgo.service.RegisterClienteService
import com.Veterinaria.Vetgo.service.RegisterVeterinarioService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/register")
class RegisterController(
    private val clienteService: RegisterClienteService,
    private val vetService: RegisterVeterinarioService
) {
    @PostMapping("/cliente")
    fun registrarCliente(@RequestBody req: RegisterClienteRequest): RegisterUsuarioResponse {
        return clienteService.registrarCliente(req)
    }

    @PostMapping("/veterinario")
    fun registrarVeterinario(@RequestBody req: RegisterVeterinarioRequest): RegisterUsuarioResponse {
        return vetService.registrarVeterinario(req)
    }
}