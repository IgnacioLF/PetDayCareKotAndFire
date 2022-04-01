package com.example.petdaycarekotandfire

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class MascotaArrayAdapter (context : Context, viewtopaint : Int, private val mascotaslist : ArrayList<pet>) : ArrayAdapter<pet> (context,viewtopaint,mascotaslist) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = LayoutInflater.from(context)
        val currentlistitem = inflater.inflate(R.layout.item_list_mascota, null)
        val textViewnombre = currentlistitem.findViewById<TextView>(R.id.textViewnombre)
        val textViewraza = currentlistitem.findViewById<TextView>(R.id.textViewraza)
        textViewnombre.text = mascotaslist.get(position).nombre
        textViewraza.text = mascotaslist.get(position).raza
        return currentlistitem
    }
}