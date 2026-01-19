package com.example.gestionincidenciascomunidad

import android.content.Intent // Necesario para navegar entre pantallas
import android.os.Bundle
import android.view.View // Importante para gestionar la visibilidad del ProgressBar
import android.widget.Toast // Para mostrar mensajes
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth // Import de Firebase Auth
import com.google.firebase.database.FirebaseDatabase // Import de Realtime Database
import com.example.gestionincidenciascomunidad.databinding.ActivityRegisterBinding // Verifica que el paquete sea el tuyo
import com.example.gestionincidenciascomunidad.models.User // Tu clase de datos

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    // Es mejor inicializarlos mediante "by lazy" o dentro del onCreate
    private val auth by lazy { FirebaseAuth.getInstance() }
    private val database by lazy { FirebaseDatabase.getInstance().getReference("Users") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCrearCuenta.setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser() {
        val email = binding.etEmail.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()

        if (email.isEmpty() || password.isEmpty()) {
            // "Toast" empieza con mayúscula, es una clase de Android
            Toast.makeText(this, "Por favor, rellena todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        // REQUISITO TÉCNICO: Mostrar ProgressBar [cite: 119, 177]
        binding.progressBar.visibility = View.VISIBLE

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val uid = auth.currentUser?.uid
                val newUser = User(
                    uid = uid,
                    nombre = binding.etNombre.text.toString(),
                    apellidos = binding.etApellidos.text.toString(),
                    email = email,
                    piso = binding.etPiso.text.toString()
                )

                // Guardar en Realtime Database [cite: 47, 48]
                uid?.let {
                    database.child(it).setValue(newUser).addOnSuccessListener {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(this, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show()

                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }.addOnFailureListener {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(this, "Error al guardar en DB", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                binding.progressBar.visibility = View.GONE
                Toast.makeText(this, "Error: ${task.exception?.message}", Toast.LENGTH_LONG).show()
            }
        }
    }
}