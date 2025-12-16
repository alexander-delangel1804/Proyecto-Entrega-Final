package com.Veterinaria.Vetgo.exception.encuestas

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class EncuestaNoEncontradaException(id: Int) : RuntimeException("La encuesta con id $id no existe")

@ResponseStatus(HttpStatus.BAD_REQUEST)
class FormatoFechaEncuestaInvalidoException : RuntimeException("Formato de fecha inválido (usar YYYY-MM-DD)")

@ResponseStatus(HttpStatus.BAD_REQUEST)
class CalificacionFueraDeRangoException : RuntimeException("La calificación debe estar entre 1 y 5")

@ResponseStatus(HttpStatus.FORBIDDEN)
class VeterinarioNoPuedeCrearEncuestaException : RuntimeException("Un veterinario no puede crear encuestas")

@ResponseStatus(HttpStatus.BAD_REQUEST)
class ClienteNoPuedeCalificarClienteException : RuntimeException("Un cliente no puede calificar a otro cliente")

@ResponseStatus(HttpStatus.CONFLICT)
class EncuestaDuplicadaException : RuntimeException("El cliente ya calificó a este veterinario")

