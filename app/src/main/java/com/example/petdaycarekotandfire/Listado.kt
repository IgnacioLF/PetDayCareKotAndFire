package com.example.petdaycarekotandfire

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.core.view.isEmpty
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Listado : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listado)
        getMascotas()

        var testpet = pet("guau","salchicha",pet.Genero.MASCULINO,"20")

        //listmascotas.add(testpet)

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
                    val bnewpet = findViewById<Button>(R.id.buttonpata)
                    bnewpet.setOnClickListener {
                        startActivity(Intent(applicationContext,newpet::class.java))
                    }
                    val currentname = document.data["name"].toString()
                    val currentraza = document.data["raza"].toString()
                    val currentgenero = document.data["genero"].toString()
                    val currentpeso = document.data["peso"].toString()
                    val currentmascota = pet(currentname,currentraza,pet.Genero.valueOf(currentgenero.toUpperCase()),currentpeso)
                    listmascotas.add(currentmascota)
                }
                val listView = findViewById<ListView>(R.id.listviewmascotas)
                listView.adapter = MascotaArrayAdapter(applicationContext,R.layout.item_list_mascota,listmascotas)
                listView.setOnItemClickListener { parent, view, position, id ->
                    var intent = Intent(applicationContext,edit::class.java)
                    intent.putExtra("mascota", listmascotas.get(position))
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