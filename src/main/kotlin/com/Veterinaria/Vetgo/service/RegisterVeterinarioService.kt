package com.Veterinaria.Vetgo.service

import com.Veterinaria.Vetgo.model.dto.RegisterUsuarioResponse
import com.Veterinaria.Vetgo.model.dto.RegisterVeterinarioRequest
import com.Veterinaria.Vetgo.model.entity.Usuario
import com.Veterinaria.Vetgo.model.entity.VeterinariosInfo
import com.Veterinaria.Vetgo.repository.UsuarioRepository
import com.Veterinaria.Vetgo.repository.VeterinariosInfoRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal

@Service
class RegisterVeterinarioService(
    private val usuarioRepo: UsuarioRepository,
    private val vetRepo: VeterinariosInfoRepository
) {

    @Transactional
    fun registrarVeterinario(req: RegisterVeterinarioRequest): RegisterUsuarioResponse {

        if (usuarioRepo.existsByCorreo(req.correo)) {
            throw IllegalArgumentException("El correo ya está registrado")
        }

        val usuario = Usuario(
            nombre = req.nombre,
            correo = req.correo,
            contrasena = req.contrasena,
            telefono = req.telefono,
            direccion = req.direccion,
            ciudad = req.ciudad,
            rol = "Veterinario"
        )

        val guardado = usuarioRepo.save(usuario)

        val vetInfo = VeterinariosInfo(
            usuario = guardado,
            cedulaProfesional = req.cedulaProfesional,
            especialidad = req.especialidad,
            estado = "Inactivo",
            califPromedio = BigDecimal.ZERO
        )

        vetRepo.save(vetInfo)

        return RegisterUsuarioResponse(
            idUsuario = guardado.idUsuario,
            mensaje = "Veterinario registrado correctamente"
        )
    }
}
