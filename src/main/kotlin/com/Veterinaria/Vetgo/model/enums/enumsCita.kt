package com.Veterinaria.Vetgo.model.enums

enum class CitaAccion {
    CREAR,
    CANCELAR_CLIENTE,
    CANCELAR_VETERINARIO,
    TOMAR,
    MARCAR_EN_CAMINO,
    INICIAR_SERVICIO,
    COMPLETAR_SERVICIO,
    MARCAR_PAGADA
}

enum class CitaEstado(val valor: String) {
    EN_ESPERA("En espera"),
    CANCELADA_CLIENTE("Cancelada por cliente"),
    ASIGNADA_VETERINARIO("Asignada a veterinario"),
    EN_CAMINO("En camino"),
    EN_SERVICIO("En servicio"),
    COMPLETADA("Completada"),
    PAGADA("Pagada"),
    CANCELADA_VETERINARIO("Cancelada por veterinario");

    companion object {
        fun from(valor: String): CitaEstado? =
            values().firstOrNull { it.valor == valor }
    }
}

