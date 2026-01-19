package com.example.gestionincidenciascomunidad.models

data class Incidencia (
    val id: String? = null,
    val titulo: String? = null,
    val descripcion: String? = null,
    val imageUrl: String? = null,    // Para Firebase Storage [cite: 118]
    val emailUsuario: String? = null, // Para el control de acceso [cite: 133]
    val nombreUsuario: String? = null
)