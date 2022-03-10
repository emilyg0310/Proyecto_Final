package com.example.proyecto_final

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class Perfil : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        val atras = findViewById<ImageButton>(R.id.atras)
        atras.setOnClickListener{
            val pase_vista = Intent(this, Inicio::class.java)
            startActivity(pase_vista)
        }
    }
}