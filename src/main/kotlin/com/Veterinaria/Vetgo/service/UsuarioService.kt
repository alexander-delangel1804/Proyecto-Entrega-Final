package com.Veterinaria.Vetgo.service

import com.Veterinaria.Vetgo.model.dto.LoginRequest
import com.Veterinaria.Vetgo.repository.UsuarioRepository
import com.Veterinaria.Vetgo.model.dto.LoginResponse
import com.Veterinaria.Vetgo.model.dto.UsuarioResponse
import com.Veterinaria.Vetgo.repository.VeterinariosInfoRepository
import org.springframework.stereotype.Service

@Service
class UsuarioService(
    private val usuarioRepository: UsuarioRepository,
    private val vetRepo: VeterinariosInfoRepository
) {

    fun login(request: LoginRequest): LoginResponse {

        val usuario = usuarioRepository.findByCorreo(request.correo)
            ?: return LoginResponse("Credenciales incorrectas", null)

        if (usuario.contrasena != request.contrasena) {
            return LoginResponse("Credenciales incorrectas", null)
        }

        val userId = usuario.idUsuario
        val vetInfo = vetRepo.findByUsuarioIdUsuario(userId)
        if (usuario.rol == "Veterinario" && vetInfo != null) {
            val estado = vetInfo.estado?.trim()
            if (estado != "Activo" && estado != "Disponible") {
                return LoginResponse("El veterinario no está autorizado", null)
            }
        }

        val usuarioResponse = UsuarioResponse(
            idUsuario = usuario.idUsuario,
            nombre = usuario.nombre,
            correo = usuario.correo,
            telefono = usuario.telefono,
            direccion = usuario.direccion,
            ciudad = usuario.ciudad,
            rol = usuario.rol
        )

        return LoginResponse("Login exitoso", usuarioResponse)
    }
}
