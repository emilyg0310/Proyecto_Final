package com.example.proyecto_final

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.proyecto_final.databinding.ActivitySesionBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Sesion : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivitySesionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySesionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FirebaseApp.initializeApp(this)
        auth = Firebase.auth

        //Se define el método para el login
        binding.iniciar.setOnClickListener{
            haceLogin();
        }

        }

    private fun actualiza(user: FirebaseUser?) {
        if (user != null){
            val intent = Intent(this, Inicio::class.java)
            startActivity(intent)
        }
    }

    //Esto hará que una vez autenticado... no pida a menos que se cierre la sesión
    public override fun onStart() {
        super.onStart()
        val usuario=auth.currentUser
        actualiza(usuario)
    }

    private fun haceLogin(){
        val usuario = binding.NomUsuario.text.toString()
        val clave = binding.ContraseAUsu.text.toString()


        //Se hace login
        auth.signInWithEmailAndPassword(usuario, clave)
            .addOnCompleteListener(this){ task ->
                if (task.isSuccessful){
                    Log.d("Autenticando", "Autenticado")
                    val user = auth.currentUser
                    actualiza(user)
                }else{
                    Log.d("Autenticando", "Falló")
                    Toast.makeText(baseContext, "Falló", Toast.LENGTH_LONG).show()
                    actualiza(null)
                }
            }
    }
    }
