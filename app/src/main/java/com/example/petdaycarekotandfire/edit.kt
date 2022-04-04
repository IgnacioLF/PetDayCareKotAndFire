package com.example.petdaycarekotandfire

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.google.firebase.auth.FirebaseAuth

class edit : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        var spinner = findViewById<Spinner>(R.id.spinnergenero)
        var generolista = arrayOf<String>("Masculino","Femenino")
        var spinadapter = ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,generolista)
        spinner.adapter=spinadapter
        var guardar = findViewById<Button>(R.id.buttonguardar)
        var intent : Intent? = intent
        var currentpet = intent?.getSerializableExtra("mascota") as pet
        val editTextnombre = findViewById<EditText>(R.id.edittextnombre)
        val editTextraza = findViewById<EditText>(R.id.edittextraza)
        val editTextconstitucion = findViewById<EditText>(R.id.editTextconstitucion)
        editTextnombre.setText(currentpet.nombre)
        editTextraza.setText(currentpet.raza)
        editTextconstitucion.setText(currentpet.peso)
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
                TODO("Not yet implemented")
            }
        }
        spinner.setSelection(currentpet.genero.ordinal)
        guardar.setOnClickListener {
            TODO("GUARDAR EN FIREBASE")
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
}

