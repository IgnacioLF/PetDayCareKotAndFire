package com.example.petdaycarekotandfire

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class edit : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        var spinner = findViewById<Spinner>(R.id.spinnergenero)
        var generolista = arrayOf<String>("Masculino","Femenino")
        var spinadapter = ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,generolista)
        spinner.adapter=spinadapter
        var guardar = findViewById<Button>(R.id.buttonguardar)
        var text = findViewById<TextView>(R.id.test)
        val posicion = ""

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
            var testpet = pet("test","test",spinner.selectedItemPosition.toInt(),"test")
            text.setText(spinner.selectedItemPosition.toString())
        }
    }
}

