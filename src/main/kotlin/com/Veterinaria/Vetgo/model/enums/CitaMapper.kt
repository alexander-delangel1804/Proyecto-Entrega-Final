package com.Veterinaria.Vetgo.model.enums

import com.Veterinaria.Vetgo.model.dto.CitaResponse
import com.Veterinaria.Vetgo.model.entity.Cita
import java.time.ZoneId

fun Cita.toResponse(): CitaResponse {
    val fechaLocal = this.fechaCita.toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDateTime()

    return CitaResponse(
        idCita = this.idCita,
        fkIdCliente = this.fkIdCliente,
        fkServicio = this.fkServicio,
        fkMascota = this.fkMascota,
        fkIdVeterinario = this.fkIdVeterinario,
        fechaCita = fechaLocal,
        estado = CitaEstado.from(this.estado) ?: CitaEstado.EN_ESPERA,
        detallesCita = this.detallesCita,
        metodoPago = this.metodoPago
    )
}

