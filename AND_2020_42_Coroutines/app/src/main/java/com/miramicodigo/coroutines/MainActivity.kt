package com.miramicodigo.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnClick1.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                executeRequest()
            }
        }
    }

    suspend fun executeRequest() = withContext(Dispatchers.IO) {
        val resultado1 = getApiResult1()
        mostrarResultadoEnHiloPrincipal(resultado1)
        val resultado2 = getApiResult2()
        mostrarResultadoEnHiloPrincipal(resultado2)
    }

    suspend fun mostrarResultadoEnHiloPrincipal(resultado: String) {
        withContext(Dispatchers.Main) {
            showResult(resultado)
        }
    }

    suspend fun getApiResult1(): String {
        log("Obtener resultado de funcion suspendida 1")
        delay(10000)  // simulacion de proceso largo (obtencion de datos de servicio web, descargar datos, etc)
        return "resultado 1"
    }

    suspend fun getApiResult2(): String {
        log("Obtener resultado de funcion suspendida 2")
        delay(4000)
        return "resultado 2"
    }

    private fun showResult(res: String){
        tv_result.text = tv_result.text.toString() + res + "\n"
    }

    private fun log(mensaje: String) {
        println("HILO ACTUAL: ${Thread.currentThread().name}: $mensaje")
    }

}