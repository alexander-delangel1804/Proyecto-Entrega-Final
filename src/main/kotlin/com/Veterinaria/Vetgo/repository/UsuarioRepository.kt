package com.Veterinaria.Vetgo.repository

import com.Veterinaria.Vetgo.model.entity.ClientesInfo
import com.Veterinaria.Vetgo.model.entity.Usuario
import com.Veterinaria.Vetgo.model.entity.VeterinariosInfo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UsuarioRepository : JpaRepository<Usuario, Int> {

    fun findByCorreo(correo: String): Usuario?

    fun findByRol(rol: String): List<Usuario>

    fun existsByCorreo(correo: String): Boolean



}


