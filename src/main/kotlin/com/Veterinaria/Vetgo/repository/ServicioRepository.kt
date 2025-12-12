package com.Veterinaria.Vetgo.repository

import com.Veterinaria.Vetgo.model.entity.Servicio
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ServicioRepository : JpaRepository<Servicio, Int> {

    fun findByNombreServicio(categoria: String): List<Servicio>


}
