package com.example.proyecto_final

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.proyecto_final.databinding.ActivityActualizarCitaBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*

class ActualizarCita : AppCompatActivity() {
    private lateinit var binding: ActivityActualizarCitaBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var cal: CalendarView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityActualizarCitaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val atras = findViewById<ImageButton>(R.id.atras)
        atras.setOnClickListener{
            val pase_vista = Intent(this, Visita::class.java)
            startActivity(pase_vista)
        }


        val spinner = findViewById<Spinner>(R.id.spinner)
        ArrayAdapter.createFromResource(this, R.array.horarios, android.R.layout.simple_spinner_item)
            .also { adapter ->
                adapter.setDropDownViewResource(R.layout.custom_spinner_dropdown)
                spinner.adapter = adapter
            }


        val pos = intent.getIntExtra("id", -1)
        val citasId = DataObject.getData(pos).citaId
        var citashora = DataObject.getData(pos).hora
        binding.spinner.setSelection(resources.getStringArray(R.array.horarios).indexOf(citashora))


        binding.reservar.setOnClickListener {
            val horacita = binding.spinner.selectedItem.toString()
            //if horacita = a seleccione horario no
            if (horacita.isNotEmpty()) {
                actualizarCita(
                    horacita,
                    citasId.toString()
                )
            } else {
                Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show()
            }

        }

        binding.eliminarci.setOnClickListener{
            eliminarCita(
                citasId.toString()
            )
        }

        auth = Firebase.auth
    }

    private fun eliminarCita(
        citaId: String
    ) {



        val user = auth.currentUser

        val uid = user!!.uid


        val db = Firebase.firestore
        db.collection("citas").document(uid).collection("citasindi").document(citaId).delete().addOnSuccessListener {

            infoUser()
            Toast.makeText(this, "Cita Eliminada", Toast.LENGTH_SHORT).show()
        }
            .addOnFailureListener {
                Toast.makeText(
                    this,
                    "Fallo al eliminar cita",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }

    private fun actualizarCita(
        horacita: String,
        citaId: String
    ) {

        val user = auth.currentUser

        val uid = user!!.uid


        val db = Firebase.firestore



        db.collection("citas").document(uid).collection("citasindi").document(citaId).update(
            "hora", horacita).addOnSuccessListener {

            infoUser()
            Toast.makeText(this, "Cita Actualizada", Toast.LENGTH_SHORT).show()
        }
            .addOnFailureListener {
                Toast.makeText(
                    this,
                    "Fallo al actualizar cita",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }

    private fun infoUser() {
        val infoUserIntent = Intent(this, Visita::class.java)
        startActivity(infoUserIntent)

    }


    private fun reload() {

    }
}