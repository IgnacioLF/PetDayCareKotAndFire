package com.example.petdaycarekotandfire

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.core.view.isEmpty
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Listado : AppCompatActivity() {
    val allids = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listado)
        getMascotas()
        val bnewpet = findViewById<ImageButton>(R.id.buttonpata)
        bnewpet.setOnClickListener {
            startActivity(Intent(applicationContext,newpet::class.java))
        }
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

    fun getMascotas(){
        val db = Firebase.firestore
        db.collection("Mascotas")
            .get()
            .addOnSuccessListener { result ->
                var listmascotas = ArrayList<pet>()
                for (document in result) {
                    val currentname = document.data["name"].toString()
                    val currentraza = document.data["raza"].toString()
                    val currentgenero = document.data["genero"].toString()
                    val currentpeso = document.data["peso"].toString()
                    val currentmascota = pet(currentname,currentraza,pet.Genero.valueOf(currentgenero.toUpperCase()),currentpeso)
                    allids.add(document.id)
                    listmascotas.add(currentmascota)
                }
                val listView = findViewById<ListView>(R.id.listviewmascotas)
                listView.adapter = MascotaArrayAdapter(applicationContext,R.layout.item_list_mascota,listmascotas)
                listView.setOnItemClickListener { parent, view, position, id ->
                    var intent = Intent(applicationContext,edit::class.java)
                    intent.putExtra("mascota", listmascotas.get(position))
                    intent.putExtra("id",allids.get(position))
                    startActivity(intent)
                }
            }
            .addOnFailureListener { exception ->
                val listView = findViewById<ListView>(R.id.listviewmascotas)
                val empty = findViewById<TextView>(R.id.textViewempty)
                listView.setEmptyView(empty)
            }

    }
}