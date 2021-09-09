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
            val retorno = callNames()
            retorno.forEach {
                binding.idNomes.text = it
                delay(2000)
            }
        }
        CoroutineScope(Dispatchers.Main).launch {
            val retornoAnimals = callAnimals()
            retornoAnimals.forEach{
                binding.idAnimais.text = it
                delay(2000)
            }
        }

    }


    suspend fun callNames(): List<String> {
        println("...antes de disparar a coroutine...")
        val listOfNames = withContext(Dispatchers.Main) {
            delay(3000)
            listOf("Arthur", "Botão", "Jaime", "Leonardo","Gabriela","Tatiane","Felipe")
        }
        println("...após disparar a coroutine e ter esperado 3 segundos...")
        return listOfNames
    }

    suspend fun callAnimals(): List<String>{
        val listOfAnimals = withContext(Dispatchers.Main){
            delay(3000)
            listOf("Cavalo","Vaca","Cachorro","Gato","Pássaro","Boi","Jacaré")
        }
        return listOfAnimals
    }
}