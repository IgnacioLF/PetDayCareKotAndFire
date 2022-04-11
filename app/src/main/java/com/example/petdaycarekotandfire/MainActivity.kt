package com.example.petdaycarekotandfire

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val acceder = findViewById<Button>(R.id.buttonacceder)
        acceder.setOnClickListener {
            login()
        }
        val bregistrar = findViewById<Button>(R.id.buttonregistrar)
        bregistrar.setOnClickListener {
            register()
        }
    }
    fun register (){
        var email =findViewById<EditText>(R.id.editTextTextEmailAddress)
        var pass = findViewById<EditText>(R.id.editTextTextPassword)
        if (email.text.isNotEmpty() && pass.text.isNotEmpty()){
            FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email.text.toString(),pass.text.toString()).addOnCompleteListener {
                    if (it.isSuccessful){
                        Toast.makeText(applicationContext, "El usuario se ha registrado con exito", Toast.LENGTH_LONG).show()
                    }else {
                        showalert()
                    }
                }
        } else {
            Toast.makeText(applicationContext, "Alguno de los campos esta vacio", Toast.LENGTH_LONG).show()
        }
    }
    fun showalert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error en la operacion")
        builder.setPositiveButton("Aceptar",null)
        val dialog = builder.create()
        dialog.show()
    }
    fun login(){
        var email =findViewById<EditText>(R.id.editTextTextEmailAddress)
        var pass = findViewById<EditText>(R.id.editTextTextPassword)
        if (email.text.isNotEmpty() && pass.text.isNotEmpty()){
            FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email.text.toString(),pass.text.toString()).addOnCompleteListener {
                    if (it.isSuccessful){
                        startActivity(Intent(applicationContext,Listado::class.java))
                    }else {
                        showalert()
                    }
                }
        } else {
            Toast.makeText(applicationContext, "Alguno de los campos esta vacio", Toast.LENGTH_LONG).show()
        }
    }
}