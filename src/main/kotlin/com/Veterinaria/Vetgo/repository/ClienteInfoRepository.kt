package com.Veterinaria.Vetgo.repository

import com.Veterinaria.Vetgo.model.entity.ClientesInfo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ClienteInfoRepository : JpaRepository<ClientesInfo, Int>
