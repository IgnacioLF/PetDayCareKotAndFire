package com.example.petdaycarekotandfire

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Editpet : AppCompatActivity() {
    lateinit var editTextnombre: EditText
    lateinit var editTextraza: EditText
    lateinit var editTextpeso: EditText
    lateinit var spinner: Spinner
    lateinit var id: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        spinner = findViewById<Spinner>(R.id.spinnergenero)
        var generolista = arrayOf<String>("Masculino","Femenino")
        var spinadapter = ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,generolista)
        spinner.adapter=spinadapter
        var guardar = findViewById<Button>(R.id.buttonguardar)
        var intent : Intent? = intent
        var currentpet = intent?.getSerializableExtra("mascota") as Pet
        editTextnombre = findViewById<EditText>(R.id.edittextnombre)
        editTextraza = findViewById<EditText>(R.id.edittextraza)
        editTextpeso = findViewById<EditText>(R.id.editTextconstitucion)
        id = intent.getStringExtra("id").toString()
        editTextnombre.setText(currentpet.nombre)
        editTextraza.setText(currentpet.raza)
        editTextpeso.setText(currentpet.peso)
        spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener{
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
        spinner.setSelection(currentpet.genero.ordinal)
        guardar.setOnClickListener {
            modifypet()
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

    fun modifypet(){
        val db = Firebase.firestore
        val mascota = hashMapOf(
            "name" to editTextnombre.text.toString(),
            "genero" to spinner.getSelectedItem().toString(),
            "raza" to editTextraza.text.toString(),
            "peso" to editTextpeso.text.toString()
        )
        db.collection("Mascotas").document(id)
            .set(mascota)
            .addOnSuccessListener { documentReference ->
                Toast.makeText(applicationContext, "La mascota fue modificada con exito", Toast.LENGTH_LONG).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(applicationContext, "Ha ocurrido un error a la hora de crear la mascota", Toast.LENGTH_LONG).show()
            }
    }
}

