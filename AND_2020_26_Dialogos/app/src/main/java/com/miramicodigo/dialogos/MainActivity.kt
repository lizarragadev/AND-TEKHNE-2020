package com.miramicodigo.dialogos

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_dialog.view.*


class MainActivity : AppCompatActivity() {

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
        // boton positivo (si, aceptar, permitir)
        // boton negativo (no, rechazar, denegar)
        // boton neutro (cancelar, cerrar)
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("Mi dialogo")
            .setMessage("Este es el contenido de mi dialogo")
            .setCancelable(false)
            .setPositiveButton("Aceptar") { dInt, i ->
                Toast.makeText(this, "Click en aceptar", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Cancelar") { dInt, i ->
                Toast.makeText(this, "Click en cancelar", Toast.LENGTH_SHORT).show()
            }
            .setNeutralButton("Cerrar") { dInt, i ->
                Toast.makeText(this, "Click en cerrar", Toast.LENGTH_SHORT).show()
            }

        return alertDialog.create()
    }


    fun crearDialogoConLista(): AlertDialog {
        val alertDialog = AlertDialog.Builder(this)
        val opciones = arrayOf(
            "Opcion 1",
            "Opcion 2",
            "Opcion 3",
            "Opcion 4",
            "Opcion 5",
            "Opcion 6"
        )
        alertDialog.setTitle("Elija una opcion")
            .setCancelable(false)
            .setItems(opciones) { dInt, i ->
                Toast.makeText(this, "Hizo click en ${opciones[i]}", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Cancelar") { dInt, i ->
                Toast.makeText(this, "Cerrar", Toast.LENGTH_SHORT).show()
            }
        return alertDialog.create()
    }

    fun crearDialogoConListaRadio(): AlertDialog {
        val alertDialog = AlertDialog.Builder(this)
        val opciones = arrayOf("Radio 1", "Radio 2", "Radio 3", "Radio 4", "Radio 5", "Radio 6")
        var posicion = 0
        alertDialog.setTitle("Elija una opcion del radio")
            .setCancelable(false)
            .setSingleChoiceItems(opciones, 0) { dInt, i ->
                posicion = i
            }
            .setPositiveButton("Aceptar") { dInt, i ->
                Toast.makeText(
                    this,
                    "Selecciono la opcion ${opciones[posicion]}",
                    Toast.LENGTH_SHORT
                ).show()
            }
            .setNegativeButton("Cancelar") { dInt, i ->
                Toast.makeText(this, "Cerrar", Toast.LENGTH_SHORT).show()
            }
        return alertDialog.create()
    }

    fun crearDialogoCheckBox(): AlertDialog {
        val alertDialog = AlertDialog.Builder(this)
        val opciones = arrayOf("Android Dev", "iOS Dev", "Python Dev", "Go Dev", "ML Dev")
        val itemSeleccionados = ArrayList<Int>()
        alertDialog.setTitle("Elige tus skills")
            .setCancelable(false)
            .setMultiChoiceItems(opciones, null) { dInt, i, b ->
                if(b) {
                    itemSeleccionados.add(i)
                } else {
                    if(itemSeleccionados.contains(i)) {
                        itemSeleccionados.remove(i)
                    }
                }
            }
            .setPositiveButton("Aceptar") { dInt, i ->
                var res = ""
                itemSeleccionados.forEach {
                    res = res + "\n ${opciones[it]}"
                }
                Toast.makeText(this, "Seleccionados $res", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Cancelar") { dInt, i ->
                Toast.makeText(this, "Cerrar", Toast.LENGTH_SHORT).show()
            }
        return alertDialog.create()
    }

    fun crearDialogoPersonalizado() {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setCancelable(false)
        val v = layoutInflater.inflate(R.layout.custom_dialog, null)
        alertDialog.setView(v)

        val dialog = alertDialog.show()

        v.btnEntrar.setOnClickListener {
            Toast.makeText(this, "Nombre: ${v.etNombre.text}", Toast.LENGTH_LONG).show()
            dialog.dismiss()
        }

        v.btnCrear.setOnClickListener {
            dialog.dismiss()
        }

    }

}