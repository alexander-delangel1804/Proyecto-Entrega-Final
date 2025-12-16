package com.Veterinaria.Vetgo.repository


import com.Veterinaria.Vetgo.model.entity.Mascota
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface MascotaRepository : JpaRepository<Mascota, Int> {
    fun findByClienteId(clienteId: Int): List<Mascota>

    fun findByIdMascota(id: Int): Optional<Mascota>


}