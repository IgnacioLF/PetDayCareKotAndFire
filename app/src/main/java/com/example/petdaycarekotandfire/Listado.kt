package com.example.petdaycarekotandfire

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.core.view.isEmpty
import com.google.firebase.auth.FirebaseAuth

class Listado : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listado)
        val listView = findViewById<ListView>(R.id.listviewmascotas)
        val empty = findViewById<TextView>(R.id.textViewempty)
        var testpet = pet("guau","salchicha",pet.Genero.MASCULINO,"20")
        var listmascotas = ArrayList<pet>()
        //listmascotas.add(testpet)
        listView.adapter = MascotaArrayAdapter(applicationContext,R.layout.item_list_mascota,listmascotas)
        listView.setEmptyView(empty)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        FirebaseAuth.getInstance().signOut()
        startActivity(Intent(applicationContext,MainActivity::class.java))
        return super.onOptionsItemSelected(item)
    }
}