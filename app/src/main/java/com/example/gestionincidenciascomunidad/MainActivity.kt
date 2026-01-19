package com.example.gestionincidenciascomunidad

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.example.gestionincidenciascomunidad.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // 1. Declaramos las variables a nivel de clase (solo una vez)
    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 2. Inicializamos ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 3. Inicializamos Firebase Auth [cite: 10]
        auth = FirebaseAuth.getInstance()

        // 4. Configuración de navegación al Registro [cite: 89]
        binding.tvGoToRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        // 5. Configuración del botón de Login [cite: 42, 88]
        binding.btnLogin.setOnClickListener {
            loginUsuario()
        }
    }

    private fun loginUsuario() {
        val email = binding.etEmail.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()

        // Validación de campos [cite: 178]
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Por favor, rellena todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        // REQUISITO: Mostrar ProgressBar al cargar [cite: 119, 177]
        binding.loginProgressBar.visibility = View.VISIBLE
        binding.btnLogin.isEnabled = false

        // Intento de inicio de sesión [cite: 71, 72]
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                // Ocultamos la barra al terminar [cite: 177]
                binding.loginProgressBar.visibility = View.GONE
                binding.btnLogin.isEnabled = true

                if (task.isSuccessful) {
                    Toast.makeText(this, "¡Bienvenido!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    finish() // Cerramos el login para que no pueda volver atrás con el botón del móvil
                } else {
                    Toast.makeText(this, "Error: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                }
            }
    }
}