package com.Veterinaria.Vetgo.service

import com.Veterinaria.Vetgo.model.dto.RegisterUsuarioRequest
import com.Veterinaria.Vetgo.model.dto.RegisterUsuarioResponse
import com.Veterinaria.Vetgo.model.entity.Usuario
import com.Veterinaria.Vetgo.model.entity.VeterinariosInfo
import com.Veterinaria.Vetgo.repository.UsuarioRepository
import com.Veterinaria.Vetgo.repository.VeterinariosInfoRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal

@Service
class RegisterService(
    private val usuarioRepo: UsuarioRepository,
    private val vetRepo: VeterinariosInfoRepository
) {

    @Transactional
    fun registrarUsuario(req: RegisterUsuarioRequest): RegisterUsuarioResponse {

        if (usuarioRepo.existsByCorreo(req.correo)) {
            throw IllegalArgumentException("El correo ya está registrado")
        }

        val rolesValidos = listOf("Cliente", "Veterinario")
        if (!rolesValidos.contains(req.rol)) {
            throw IllegalArgumentException("Rol inválido. Debe ser Cliente o Veterinario")
        }

        if (req.rol == "Veterinario") {
            if (req.cedulaProfesional.isNullOrBlank() || req.especialidad.isNullOrBlank()) {
                throw IllegalArgumentException("Los veterinarios deben proporcionar cédula profesional y especialidad")
            }
        }

        val usuario = Usuario(
            nombre = req.nombre,
            correo = req.correo,
            contrasena = req.contrasena,
            telefono = req.telefono,
            direccion = req.direccion,
            ciudad = req.ciudad,
            rol = req.rol
        )

        val guardado = usuarioRepo.save(usuario)

        if (req.rol == "Veterinario") {
            val vetInfo = VeterinariosInfo(
                usuario = guardado,
                cedulaProfesional = req.cedulaProfesional!!,
                especialidad = req.especialidad!!,
                estado = "Inactivo",
                califPromedio = BigDecimal.ZERO
            )
            vetRepo.save(vetInfo)
        }


        return RegisterUsuarioResponse(
            idUsuario = guardado.idUsuario,
            mensaje = "Usuario registrado correctamente"
        )
    }
}
