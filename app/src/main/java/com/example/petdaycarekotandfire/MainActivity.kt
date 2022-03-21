package com.example.petdaycarekotandfire

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.analytics.FirebaseAnalytics

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val test = FirebaseAnalytics.getInstance(this)
        val acceder = findViewById<Button>(R.id.buttonacceder)
        acceder.setOnClickListener { startActivity(Intent(applicationContext,edit::class.java))}
    }
}