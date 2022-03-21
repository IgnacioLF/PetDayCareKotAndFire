package com.example.petdaycarekotandfire

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner

class edit : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        var spinner = findViewById<Spinner>(R.id.spinnergenero)
        var generolista = listOf<String>("Masculino","Femenino")
        var spinadapter = ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,generolista)
        spinner.adapter=spinadapter
    }
}