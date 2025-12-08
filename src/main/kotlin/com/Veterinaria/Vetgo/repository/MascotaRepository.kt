package com.Veterinaria.Vetgo.repository


import com.Veterinaria.Vetgo.model.entity.Mascota
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MascotaRepository : JpaRepository<Mascota, Int> {
    fun findByUsuarioId(usuarioId: Int): List<Mascota>

    fun existsByNombreAndUsuarioId(nombre: String, usuarioId: Int): Boolean
}