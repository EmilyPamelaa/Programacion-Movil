package com.example.healthcalculator

fun clasificarIMC(peso: Double, altura: Double): String {
    if (altura <= 0.0) return "Datos inválidos"
    val imc = peso / (altura * altura)
    return when {
        imc < 18.5 -> "Bajo peso"
        imc in 18.5..24.9 -> "Peso normal"
        imc in 25.0..29.9 -> "Sobrepeso"
        else -> "Obesidad"
    }
}