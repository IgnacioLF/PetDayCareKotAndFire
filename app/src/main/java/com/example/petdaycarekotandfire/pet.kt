package com.example.petdaycarekotandfire

import java.io.Serializable

class pet(var nombre:String, var raza:String, var genero: Genero, var peso: String) : Serializable{
    enum class Genero{
        MASCULINO,FEMENINO
    }
}