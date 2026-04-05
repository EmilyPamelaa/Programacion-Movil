package com.example.healthcalculator

import androidx.compose.material3.MaterialTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.TextField
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.healthcalculator.ui.theme.HealthCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HealthCalculatorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HealthCalculatorScreen(Modifier.padding(innerPadding))
                }
            }
        }
    }
}
@Composable
fun HealthCalculatorScreen(modifier: Modifier = Modifier) {
    var pesoTxt by remember { mutableStateOf("") }
    var alturaTxt by remember { mutableStateOf("") }
    val peso = pesoTxt.toDoubleOrNull() ?: 0.0
    val altura = alturaTxt.toDoubleOrNull() ?: 0.0
    val resultado = when {
        (peso <= 0.0 && pesoTxt.any { it.isLetter() }) || (altura <= 0.0 && alturaTxt.any { it.isLetter() }) -> "Error: Ingrese solo números"
        peso <= 0.0 && altura <= 0.0 -> "Esperando datos..."
        peso <= 0.0 -> "Por favor, ingrese el peso"
        altura <= 0.0 -> "Por favor, ingrese la altura"
        else -> clasificarIMC(peso, altura)
    }
Column(modifier = modifier.padding(16.dp)) {
    TextField(
        value = pesoTxt,
        onValueChange = { pesoTxt = it },
        label = { Text("Peso (kg)") })
    Spacer(
        modifier = Modifier.height(10.dp)
    )

    TextField(
        value = alturaTxt,
        onValueChange = { alturaTxt = it },
        label = { Text("Altura (m)") }
    )

    Spacer(modifier = Modifier.height(20.dp))

    Text(
        text = resultado,
        style = MaterialTheme.typography.headlineMedium,
        color = when {
            resultado.contains("Error") -> Color.Red
            resultado.contains("Por favor") || resultado.contains("Esperando") -> Color.Blue
            resultado.contains("Obesidad") -> Color(0xFFB71C1C)
            resultado.contains("Sobrepeso") -> Color(0xFFF57C00)
            resultado.contains("Peso normal") -> Color(0xFF2E7D32)
            resultado.contains("Bajo peso") -> Color(0xFF1976D2)

            else -> Color.Black
        }
    )}
}


