package com.Veterinaria.Vetgo.repository

import com.Veterinaria.Vetgo.model.entity.Pago
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PagoRepository : JpaRepository<Pago, Int> {


}