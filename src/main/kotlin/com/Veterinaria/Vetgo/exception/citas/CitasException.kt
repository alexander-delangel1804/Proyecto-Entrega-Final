package com.Veterinaria.Vetgo.exception.citas

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class CitaNoEncontradaException(idCita: Int) : RuntimeException("La cita con id $idCita no existe")

@ResponseStatus(HttpStatus.CONFLICT)
class AccionCitaNoPermitidaException(mensaje: String) : RuntimeException(mensaje)

@ResponseStatus(HttpStatus.BAD_REQUEST)
class VeterinarioRequeridoException : RuntimeException("Se requiere el ID del veterinario para esta acción")

@ResponseStatus(HttpStatus.CONFLICT)
class EstadoCitaInvalidoException(estadoActual: String, estadoRequerido: String) : RuntimeException(
    "La cita debe estar en '$estadoRequerido' para continuar. Estado actual: $estadoActual"
)

@ResponseStatus(HttpStatus.BAD_REQUEST)
class FormatoFechaInvalidoException : RuntimeException("Formato inválido de fecha (usar YYYY-MM-DD)")


@ResponseStatus(HttpStatus.FORBIDDEN)
class MascotaNoPerteneceAlClienteException : RuntimeException("La mascota no pertenece al cliente")

@ResponseStatus(HttpStatus.CONFLICT)
class ClienteConCitaPendienteException : RuntimeException("El cliente ya tiene una cita pendiente")



