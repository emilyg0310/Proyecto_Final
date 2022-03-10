package com.example.proyecto_final

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.core.util.PatternsCompat
import com.example.proyecto_final.databinding.ActivityRegistroBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class Registro : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var fStore: FirebaseFirestore
    private lateinit var binding: ActivityRegistroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FirebaseApp.initializeApp(this)
        auth = Firebase.auth
        fStore = FirebaseFirestore.getInstance()



        binding.registrarse.setOnClickListener{
            haceRegister();
        }
    }

    private fun haceRegister() {
        val nombre = binding.NomUsuario.text.toString()
        val apellidos = binding.ContraseAUsu.text.toString()
        val email = binding.Correo.text.toString()
        val cedula = binding.Cedula.text.toString()
        val clave = binding.ContraseA.text.toString()
        val barraProgre = ProgressDialog(baseContext)
        if (!inputCheck(nombre, apellidos, email, cedula, clave)) {
            Toast.makeText(baseContext, "Por favor llenar todos los campos", Toast.LENGTH_LONG)
                .show()
        } else if (nombre.isEmpty() || nombre.length < 2) {
            Toast.makeText(baseContext, "Nombre no válido", Toast.LENGTH_LONG)
                .show()
        } else if (apellidos.isEmpty() || apellidos.length < 3) {
            Toast.makeText(baseContext, "Apellidos no válidos", Toast.LENGTH_LONG)
                .show()
        } else if (email.isEmpty() || !PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(baseContext, "Email no válido", Toast.LENGTH_LONG)
                .show()
        } else if (cedula.isEmpty() || cedula.length < 7) {
            Toast.makeText(baseContext, "Cédula no válida", Toast.LENGTH_LONG)
                .show()
        } else if (clave.isEmpty() || clave.length < 6) {
            Toast.makeText(baseContext, "Clave no válida, mínimo 7 cáracteres", Toast.LENGTH_LONG)
                .show()
        } else {


            //Se hace registro
            auth.createUserWithEmailAndPassword(email, clave)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Log.d("creando usuario", "Registrado")
                        val user = auth.currentUser
                        val userID = user?.let { user.uid }.toString()
                        val documentReference = fStore.collection("Usuarios").document(userID)
                        val datauser = hashMapOf(
                            "nombre" to nombre,
                            "apellidos" to apellidos,
                            "cedula" to cedula
                        )
                        documentReference.set(datauser).addOnCompleteListener(){
                            Log.d("Registro", "Información extra del usuario creada")
                            barraProgre.dismiss()
                            Toast.makeText(baseContext, "Registro Correctamente", Toast.LENGTH_LONG).show()
                            actualiza(user)
                        }.addOnFailureListener(){
                            Toast.makeText(baseContext, "Hubo un fallo en guardar datos personales", Toast.LENGTH_LONG).show()
                        }




                    } else {
                        Log.d("creando usuario", "Falló")
                        Toast.makeText(baseContext, "Falló", Toast.LENGTH_LONG).show()
                        actualiza(null)
                    }
                }
        }
    }

    private fun actualiza(user: FirebaseUser?) {
        if (user != null){
            val intent = Intent(this, Inicio::class.java)
            startActivity(intent)
        }
    }

    private fun inputCheck(nombre: String, apellidos: String, email: String, cedula: String, clave: String): Boolean {
        return !(TextUtils.isEmpty(nombre) && TextUtils.isEmpty(apellidos) && TextUtils.isEmpty(email) && TextUtils.isEmpty(cedula) && TextUtils.isEmpty(clave))
    }
}