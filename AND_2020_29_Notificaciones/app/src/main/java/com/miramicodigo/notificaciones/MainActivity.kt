package com.miramicodigo.notificaciones

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        btnNotificacionSimple.setOnClickListener(this)
        btnNotificacionGrande.setOnClickListener(this)
        btnNotificacionAcciones.setOnClickListener(this)
        btnNotificacionImagenGrande.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnNotificacionSimple -> createSimpleNotification(this)
            R.id.btnNotificacionGrande -> createExpandableNotification(this)
            R.id.btnNotificacionImagenGrande -> createBigImageNotification(this)
            R.id.btnNotificacionAcciones -> createButtonNotification(this)
        }
    }

    private fun createSimpleNotification(context: Context) {

    }

    fun playNotificationSound() {

    }

    private fun createExpandableNotification(context: Context) {

    }

    private fun createBigImageNotification(context: Context) {

    }

    private fun createButtonNotification(context: Context) {

    }

}