package com.example.gestionincidenciascomunidad.models

// Esta clase sirve de "molde" para guardar y leer datos de Firebase Realtime Database
data class User(
    val uid: String? = null,
    val nombre: String? = null,
    val apellidos: String? = null,
    val email: String? = null,
    val piso: String? = null,
    val telefono: String? = null,
    val rol: String = "usuario" // Por defecto es 'usuario'. 'admin' se cambia manualmente.
)