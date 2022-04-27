package com.example.proyecto_final

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.proyecto_final.databinding.ActivityPerfilBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Perfil : AppCompatActivity() {
    private lateinit var binding: ActivityPerfilBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_perfil)

        val atras = findViewById<ImageButton>(R.id.atras)
        atras.setOnClickListener{
            val pase_vista = Intent(this, Inicio::class.java)
            startActivity(pase_vista)


        }
        auth = Firebase.auth

        val currentUser = auth.currentUser
        val uid = currentUser!!.uid
        val db = Firebase.firestore

        db.collection("infousuarios").document(uid).get().addOnSuccessListener {
            binding.nombre.setText(it.get("nombre") as String?)
            binding.apellido.setText(it.get("apellidos") as String?)
            binding.cedulaUsua.setText(it.get("cedula") as String?)
            binding.correoUsua.setText(it.get("telefono") as String?)
            binding.nacimiento.setText(it.get("nacimiento") as String?)
            binding.telefono.setText(it.get("genero") as String?)
            binding.telefono2.setText(it.get("direccion") as String?)
            binding.telefono3.setText(it.get("contaemerg") as String?)
            binding.telefono4.setText(it.get("telemerg") as String?)

        }

        binding.guardar.setOnClickListener {
            actualizarCita()
        }
    }

    private fun actualizarCita(
    ) {

        val user = auth.currentUser

        val uid = user!!.uid


        val db = Firebase.firestore

        val nombre = binding.nombre.text.toString()
        val apellidos = binding.apellido.text.toString()
        val cedula = binding.cedulaUsua.text.toString()
        val telefono = binding.correoUsua.text.toString()
        val nacimiento = binding.nacimiento.text.toString()
        val genero = binding.telefono.text.toString()
        val direccion = binding.telefono2.text.toString()
        val contaemerg = binding.telefono3.text.toString()
        val telemerg = binding.telefono4.text.toString()

        db.collection("infousuarios").document(uid).update("nombre", nombre,
            "apellidos", apellidos,
        "cedula", cedula,
        "telefono", telefono,
        "nacimiento", nacimiento,
        "genero", genero,
        "direccion", direccion,
        "contaemerg", contaemerg,
        "telemerg", telemerg).addOnSuccessListener {

            infoUser()
            Toast.makeText(this, "Datos Actualizados", Toast.LENGTH_SHORT).show()
        }
            .addOnFailureListener {
                Toast.makeText(
                    this,
                    "Fallo al actualizar los datos",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }

    private fun infoUser() {
        val infoUserIntent = Intent(this, Inicio::class.java)
        startActivity(infoUserIntent)

    }
}