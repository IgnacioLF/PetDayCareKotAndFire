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

class newpet : AppCompatActivity() {

    lateinit var editTextnombre: EditText
    lateinit var editTextraza: EditText
    lateinit var editTextpeso: EditText
    lateinit var spinner: Spinner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_newpet)
        editTextnombre = findViewById(R.id.edittextnombre)
        editTextraza = findViewById(R.id.edittextraza)
        editTextpeso = findViewById(R.id.editTextconstitucion)
        spinner = findViewById(R.id.spinnergenero)
        var generolista = listOf<String>("Masculino","Femenino")
        var spinadapter = ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,generolista)
        spinner.adapter=spinadapter
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
        var bnueva = findViewById<Button>(R.id.buttonnueva)
        bnueva.setOnClickListener {
            if (emptyFields()){
                Toast.makeText(applicationContext, "Alguno de los campos esta vacio", Toast.LENGTH_LONG).show()
            } else {
                nuevaMascota()
            }
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
    private fun emptyFields() : Boolean {
        if (editTextnombre.text.toString().isEmpty()
            || editTextpeso.text.toString().isEmpty()
            || editTextraza.text.toString().isEmpty()){
            return true
        }
        return false
    }

    private fun  nuevaMascota(){
        val db = Firebase.firestore
        val mascota = hashMapOf(
            "name" to editTextnombre.text.toString(),
            "genero" to spinner.getSelectedItem().toString(),
            "raza" to editTextraza.text.toString(),
            "peso" to editTextpeso.text.toString()
        )
        db.collection("Mascotas")
            .add(mascota)
            .addOnSuccessListener { documentReference ->
                Toast.makeText(applicationContext, "La mascota fue creada con exito", Toast.LENGTH_LONG).show()
                startActivity(Intent(applicationContext,Listado::class.java))
            }
            .addOnFailureListener { e ->
                Toast.makeText(applicationContext, "Ha ocurrido un error a la hora de crear la mascota", Toast.LENGTH_LONG).show()
            }
    }
}