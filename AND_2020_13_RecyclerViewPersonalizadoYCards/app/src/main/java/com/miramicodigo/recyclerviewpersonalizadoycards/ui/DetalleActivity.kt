package com.miramicodigo.recyclerviewpersonalizadoycards.ui

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.miramicodigo.recyclerviewpersonalizadoycards.R
import com.miramicodigo.recyclerviewpersonalizadoycards.model.Pokemon
import kotlinx.android.synthetic.main.activity_detalle.*

class DetalleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle)

        val pokemon = intent.getSerializableExtra("poke") as Pokemon

        val fontBold = Typeface.createFromAsset(assets, "product_sans_bold.ttf")
        val fontReg = Typeface.createFromAsset(assets, "product_sans_regular.ttf")

        tvDetalleTitulo.typeface = fontBold
        tvDetalleSubtitulo.typeface = fontReg

        tvDetalleTitulo.text = pokemon.titulo
        tvDetalleSubtitulo.text = pokemon.subtitulo
        ivDetalleImagen.setImageResource(pokemon.imagen)
    }
}