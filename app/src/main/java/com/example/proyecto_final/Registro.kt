package com.example.proyecto_final

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Registro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        val registrado= findViewById<Button>(R.id.registrarse)
        registrado.setOnClickListener{
        val pase_vista = Intent(this, Sesion::class.java)
        startActivity(pase_vista)
        }
    }
}