package com.example.coroutines_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.coroutines_test.ui.main.MainFragment
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)


        CoroutineScope(Dispatchers.Default).launch {
            val retorno = callServer()
            retorno.forEach {
                println(it)//retorna os nomes
            }
        }
    }

    suspend fun callServer(): List<String> {
        println("...antes de disparar a coroutine...")
        val listOfNames = withContext(Dispatchers.Default) {
            delay(3000)
            listOf("Arthur", "Botão", "Jaime", "Leonardo")
        }
        println("...após disparar a coroutine e ter esperado 3 segundos...")
        return listOfNames
    }
}