package com.miramicodigo.listaspersonalizadas.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.miramicodigo.listaspersonalizadas.R
import com.miramicodigo.listaspersonalizadas.model.Pokemon
import kotlinx.android.synthetic.main.activity_detalle.*

class DetalleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle)

        val pokemon = intent.getSerializableExtra("poke") as Pokemon

        tvDetalleTitulo.text = pokemon.titulo
        tvDetalleSubtitulo.text = pokemon.subtitulo
        ivDetalleImagen.setImageResource(pokemon.imagen)
    }
}