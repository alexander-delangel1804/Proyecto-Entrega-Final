package com.Veterinaria.Vetgo.service

import com.Veterinaria.Vetgo.model.dto.RegisterClienteRequest
import com.Veterinaria.Vetgo.model.dto.RegisterUsuarioResponse
import com.Veterinaria.Vetgo.model.dto.RegisterVeterinarioRequest
import com.Veterinaria.Vetgo.model.entity.ClientesInfo
import com.Veterinaria.Vetgo.model.entity.Usuario
import com.Veterinaria.Vetgo.model.entity.VeterinariosInfo
import com.Veterinaria.Vetgo.repository.ClienteInfoRepository
import com.Veterinaria.Vetgo.repository.UsuarioRepository
import com.Veterinaria.Vetgo.repository.VeterinariosInfoRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal
import java.time.LocalDateTime

@Service
class RegisterClienteService(
    private val usuarioRepo: UsuarioRepository,
    private val clienteRepo: ClienteInfoRepository
) {

    @Transactional
    fun registrarCliente(req: RegisterClienteRequest): RegisterUsuarioResponse {

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
            rol = "Cliente"
        )

        val guardado = usuarioRepo.save(usuario)

        val clienteInfo = ClientesInfo(
            cliente = guardado
        )

        clienteRepo.save(clienteInfo)

        return RegisterUsuarioResponse(
            idUsuario = guardado.idUsuario,
            mensaje = "Cliente registrado correctamente"
        )
    }
}
