package com.example.proyecto_final

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.proyecto_final.databinding.ActivityReservarBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*

class Reservar : AppCompatActivity() {
    private lateinit var binding: ActivityReservarBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var cal: CalendarView
    var fecha: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReservarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val atras = findViewById<ImageButton>(R.id.atras)
        atras.setOnClickListener{
            val pase_vista = Intent(this, Inicio::class.java)
            startActivity(pase_vista)
        }


        val spinner = findViewById<Spinner>(R.id.spinner)
        ArrayAdapter.createFromResource(this, R.array.horarios, android.R.layout.simple_spinner_item)
            .also { adapter ->
                adapter.setDropDownViewResource(R.layout.custom_spinner_dropdown)
                spinner.adapter = adapter
            }

        cal = findViewById(R.id.calendarView)

        cal.setOnDateChangeListener { calendarview, year, month, dayOfMonth ->
            fecha = dayOfMonth.toString() + "/" + (month+1).toString() + "/" + year.toString()
        }




        binding.reservar.setOnClickListener {
            val horacita = binding.spinner.selectedItem.toString()
            //if horacita = a seleccione horario no
            if (fecha.isNotEmpty() && horacita.isNotEmpty()) {
                registrarCita(
                    fecha,
                    horacita
                )
            } else {
                Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show()
            }

        }

        auth = Firebase.auth
    }



    private fun registrarCita(
        fecha: String,
        horacita: String
    ) {

        val user = auth.currentUser

        val uid = user!!.uid
        val citaId = UUID.randomUUID().toString()
        val map = hashMapOf(
            "fecha" to fecha,
            "hora" to horacita,
            "citaId" to citaId

        )
        val db = Firebase.firestore

        db.collection("citas").document(uid).collection("citasindi").document(citaId).set(map).addOnSuccessListener {

            infoUser()
            Toast.makeText(this, "Cita Reservada", Toast.LENGTH_SHORT).show()
        }
            .addOnFailureListener {
                Toast.makeText(
                    this,
                    "Fallo al reservar cita",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }

    private fun infoUser() {
        val infoUserIntent = Intent(this, Inicio::class.java)
        startActivity(infoUserIntent)

    }


    private fun reload() {

    }

    }




