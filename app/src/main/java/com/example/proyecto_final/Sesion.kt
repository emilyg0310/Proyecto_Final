package com.example.proyecto_final

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Sesion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sesion)

        val sesion = findViewById<Button>(R.id.iniciar)
        sesion.setOnClickListener{
            val pase_vista = Intent(this, Inicio::class.java)
            startActivity(pase_vista)
        }

        val registrar= findViewById<Button>(R.id.registrar)
        registrar.setOnClickListener{
            val pase_vista = Intent(this, Registro::class.java)
            startActivity(pase_vista)
        }

        val registrado= findViewById<Button>(R.id.registrarse)
        registrado.setOnClickListener{
            val pase_vista = Intent(this, Registro::class.java)
            startActivity(pase_vista)
        }
    }
}