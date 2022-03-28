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
        // temp
        var text = findViewById<TextView>(R.id.test)
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

        guardar.setOnClickListener {
            var testpet = pet("test","test",,"test")
            text.setText(testpet.)
            // spinner.selectedItemPosition.toInt()
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

