package com.example.proyecto_final

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val citaList: ArrayList<Cita>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.view, parent, false)
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentitem = citaList[position]
        holder.fecha.text = currentitem.fecha
        holder.hora.text = currentitem.hora
        holder.citaId.text = currentitem.citaId


        holder.itemView.setOnClickListener{
            val intent= Intent(holder.itemView.context,ActualizarCita::class.java)
            intent.putExtra("id",position)
            holder.itemView.context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return citaList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val fecha : TextView = itemView.findViewById(R.id.fecha)
        val hora : TextView = itemView.findViewById(R.id.hora)
        val citaId : TextView = itemView.findViewById(R.id.citaId)

    }
}