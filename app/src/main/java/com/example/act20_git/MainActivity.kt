package com.example.act20_git

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var btnDescarregar: Button
    private lateinit var tvEstat: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDescarregar = findViewById(R.id.btnDescarregar)
        tvEstat = findViewById(R.id.tvEstat)

        btnDescarregar.setOnClickListener {
            iniciarDescarrega()
        }
    }

    private fun iniciarDescarrega() {
        btnDescarregar.isEnabled = false
        tvEstat.text = "Descargando..."

        // Coroutines para simular la descarga
        CoroutineScope(Dispatchers.Main).launch {
            // Ejecutar en hilo secundario con Dispatchers.IO
            val resultado = withContext(Dispatchers.IO) {
                simularDescarrega()
            }

            // Actualizar UI en el hilo principal
            tvEstat.text = resultado
            btnDescarregar.isEnabled = true
        }
    }

    private suspend fun simularDescarrega(): String {
        // Delay aleatorio entre 2 y 5 segundos
        val delayMilisegons = Random.nextLong(2000, 5000)
        delay(delayMilisegons)
        return "Descargado correctamente!"
    }
}