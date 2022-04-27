package com.example.proyecto_final

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto_final.databinding.ActivityVisitaBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.*
import com.google.firebase.ktx.Firebase
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.ktx.firestore

class Visita : AppCompatActivity() {
    private lateinit var binding: ActivityVisitaBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var fStore: FirebaseFirestore
    private lateinit var citaArrayList: ArrayList<Cita>
    private lateinit var citaRecyclerView: RecyclerView
    private lateinit var myAdapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_visita)

        auth = Firebase.auth



        citaRecyclerView = findViewById(R.id.recycler_view)
        citaRecyclerView.layoutManager = LinearLayoutManager(this)
        citaRecyclerView.setHasFixedSize(true)

        citaArrayList = arrayListOf()

        myAdapter = MyAdapter(citaArrayList)
        citaRecyclerView.adapter = myAdapter
        DataObject.deleteAll()
        EventChangeListener()

        val atras = findViewById<ImageButton>(R.id.atras)
        atras.setOnClickListener{
            val pase_vista = Intent(this, Inicio::class.java)
            startActivity(pase_vista)
        }

    }




        private fun EventChangeListener() {

            val currentUser = auth.currentUser
            val uid = currentUser!!.uid

            fStore = FirebaseFirestore.getInstance()
            fStore.collection("citas").document(uid).collection("citasindi").
            addSnapshotListener(object : EventListener<QuerySnapshot>{
                override fun onEvent(
                    value: QuerySnapshot?,
                    error: FirebaseFirestoreException?
                ) {

                    if (error != null){

                        Log.e("Firestore Error",error.message.toString())
                        return

                    }

                    for (dc : DocumentChange in value?.documentChanges!!){


                        if (dc.type == DocumentChange.Type.ADDED){

                            citaArrayList.add(dc.document.toObject(Cita::class.java))
                            DataObject.listdatos.add(dc.document.toObject(Cita::class.java))
                        }
                    }

                    myAdapter.notifyDataSetChanged()
                }



            })

        }

}