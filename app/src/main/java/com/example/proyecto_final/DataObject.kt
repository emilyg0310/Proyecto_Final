package com.example.proyecto_final

object DataObject {
    var listdatos = mutableListOf<Cita>()

    fun setData(fecha: String, hora: String, citaId: String) {
        listdatos.add(Cita(fecha, hora, citaId))
    }

    fun getAllData(): List<Cita> {
        return listdatos
    }

    fun deleteAll(){
        listdatos.clear()
    }

    fun getData(pos:Int): Cita {
        return listdatos[pos]
    }

    fun deleteData(pos:Int){
        listdatos.removeAt(pos)
    }

    fun updateData(pos:Int,hora:String, citaId: String)
    {
        listdatos[pos].hora=hora
        listdatos[pos].citaId=citaId
    }
}