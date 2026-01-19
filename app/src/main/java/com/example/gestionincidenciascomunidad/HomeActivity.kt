package com.example.gestionincidenciascomunidad

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.example.gestionincidenciascomunidad.databinding.ActivityHomeBinding
import com.example.gestionincidenciascomunidad.models.Incidencia

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var dbRef: DatabaseReference

    // Lista para almacenar las incidencias que descargamos de Firebase
    private val listaIncidencias = mutableListOf<Incidencia>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        dbRef = FirebaseDatabase.getInstance().getReference("Incidencias")

        // Configuración del RecyclerView (obligatorio según el documento)
        binding.rvIncidencias.layoutManager = LinearLayoutManager(this)
        binding.rvIncidencias.setHasFixedSize(true)

        // Botón para ir a registrar una nueva incidencia
        binding.btnHome.setOnClickListener {
            // Aquí navegaremos a la Activity que crearemos para el formulario
            val intent = Intent(this, NuevaIncidenciaActivity::class.java)
            startActivity(intent)
        }

        obtenerIncidencias()
    }

    private fun obtenerIncidencias() {
        // Requisito técnico: Mostrar ProgressBar al cargar datos
        binding.homeProgressBar.visibility = View.VISIBLE

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                listaIncidencias.clear()
                if (snapshot.exists()) {
                    for (incidenciaSnap in snapshot.children) {
                        val data = incidenciaSnap.getValue(Incidencia::class.java)

                        // Lógica de Control de Acceso (Página 4 del PDF)
                        // Por ahora añadimos todas, pero aquí filtrarás según el rol
                        data?.let { listaIncidencias.add(it) }
                    }

                    // Aquí iría la conexión con el Adapter (que crearemos a continuación)
                    binding.homeProgressBar.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                binding.homeProgressBar.visibility = View.GONE
                Toast.makeText(this@HomeActivity, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}