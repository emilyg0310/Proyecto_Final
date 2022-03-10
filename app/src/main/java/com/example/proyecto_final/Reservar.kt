package com.example.proyecto_final

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.Spinner

class Reservar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservar)

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


    }

}