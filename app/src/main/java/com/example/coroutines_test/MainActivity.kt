package com.example.coroutines_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.coroutines_test.databinding.MainActivityBinding
import com.example.coroutines_test.ui.main.MainFragment
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    lateinit var binding : MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)


        CoroutineScope(Dispatchers.Main).launch {
            val retorno = callServer()
            retorno.forEach {
                binding.textView.text = it
                delay(2000)
            }
        }
    }

    suspend fun callServer(): List<String> {
        println("...antes de disparar a coroutine...")
        val listOfNames = withContext(Dispatchers.Main) {
            delay(3000)
            listOf("Arthur", "Botão", "Jaime", "Leonardo")
        }
        println("...após disparar a coroutine e ter esperado 3 segundos...")
        return listOfNames
    }
}