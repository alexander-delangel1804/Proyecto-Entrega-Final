package com.Veterinaria.Vetgo.controller

import com.Veterinaria.Vetgo.model.entity.Usuario
import com.Veterinaria.Vetgo.service.UsuarioService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/usuarios")
class UsuarioController(
    private val usuarioService: UsuarioService
) {

    @GetMapping("")
    fun obtenerTodosUsuarios(): ResponseEntity<List<Usuario>> =
        ResponseEntity.ok(usuarioService.obtenerTodosUsuarios())

    @GetMapping("/{id}")
    fun obtenerPorId(@PathVariable id: Int): ResponseEntity<Usuario> =
        ResponseEntity.ok(usuarioService.obtenerPorId(id))

    @GetMapping("/clientes")
    fun obtenerClientes(): ResponseEntity<List<Usuario>> =
        ResponseEntity.ok(usuarioService.obtenerClientes())


    @GetMapping("/veterinarios")
    fun obtenerVeterinarios(): ResponseEntity<List<Usuario>> =
        ResponseEntity.ok(usuarioService.obtenerVeterinarios())

    @GetMapping("/clientes/{id}")
    fun obtenerClientePorId(@PathVariable id: Int): ResponseEntity<Usuario> =
        ResponseEntity.ok(usuarioService.obtenerClientePorId(id))

    @GetMapping("/veterinarios/{id}")
    fun obtenerVeterinarioPorId(@PathVariable id: Int): ResponseEntity<Usuario> =
        ResponseEntity.ok(usuarioService.obtenerVeterinarioPorId(id))
}