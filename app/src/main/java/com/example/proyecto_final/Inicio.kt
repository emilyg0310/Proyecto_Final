package com.example.proyecto_final

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageButton
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Inicio : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)

        val reservo = findViewById<ImageButton>(R.id.reserva)
        reservo.setOnClickListener{
            val pase_vista = Intent(this, Reservar::class.java)
            startActivity(pase_vista)
        }
        val visita = findViewById<ImageButton>(R.id.visita)
        visita.setOnClickListener{
            val pase_vista = Intent(this, Visita::class.java)
            startActivity(pase_vista)
        }

        val perfil = findViewById<ImageButton>(R.id.perfil)
        perfil.setOnClickListener{
            val pase_vista = Intent(this, Perfil::class.java)
            startActivity(pase_vista)
        }

        val cerrar = findViewById<ImageButton>(R.id.cerrar)
        cerrar.setOnClickListener{
            val pase_vista = Intent(this, Sesion::class.java)
            startActivity(pase_vista)
            Firebase.auth.signOut()
            finish()
        }

    }

}