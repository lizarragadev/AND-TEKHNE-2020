package com.miramicodigo.dialogos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var btnCrear: Button? = null
    private var btnEntrar: Button? = null
    private var etNombre: EditText? = null
    private var etContrasenia: EditText? = null
    private var posicion: Int = 0
    private var res: String? = null
    private var cbRecordar: CheckBox? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDialogoSimple.setOnClickListener{
            crearDialogoSimple().show()
        }

        btnDialogoConLista.setOnClickListener {
            crearDialogoConLista().show()
        }

        btnDialogoConListaRadio.setOnClickListener {
            crearDialogoConListaRadio().show()
        }

        btnDialogoConListaCheckbox.setOnClickListener {
            crearDialogoCheckBox().show()
        }

        btnDialogoPersonalizado.setOnClickListener {
            crearDialogoPersonalizado()
        }
    }

    fun crearDialogoSimple(): AlertDialog {

        return null!!
    }


    fun crearDialogoConLista(): AlertDialog {

        return null!!
    }

    fun crearDialogoConListaRadio(): AlertDialog {

        return null!!
    }

    fun crearDialogoCheckBox(): AlertDialog {

        return null!!
    }

    fun crearDialogoPersonalizado(): AlertDialog {

        return null!!
    }
}