package com.Veterinaria.Vetgo.repository

import com.Veterinaria.Vetgo.model.entity.VeterinariosInfo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface VeterinariosInfoRepository : JpaRepository<VeterinariosInfo, Int> {

    fun findByEspecialidad(especialidad: String): List<VeterinariosInfo>

    fun findByUsuarioIdUsuario(usuarioId: Int): VeterinariosInfo?
}

