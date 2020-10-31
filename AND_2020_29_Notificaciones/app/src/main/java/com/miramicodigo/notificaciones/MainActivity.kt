package com.miramicodigo.notificaciones

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var mNotificationManager: NotificationManager
    val CANAL_ID = "miCanal01"
    val CANAL_NOMBRE = "Mi Canal de Notificaciones"
    val CANAL_DESCRIPCION = "Este es una canal para mis notificaciones"
    val NOTIFICATION_ID = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mNotificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importacia = NotificationManager.IMPORTANCE_DEFAULT
            val canal = NotificationChannel(CANAL_ID, CANAL_NOMBRE, importacia)
            canal.description = CANAL_DESCRIPCION
            canal.enableLights(true)
            canal.lightColor = Color.RED
            canal.enableVibration(true)
            canal.lockscreenVisibility = NotificationCompat.VISIBILITY_PUBLIC
            mNotificationManager.createNotificationChannel(canal)
        }

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
        val notificacion = NotificationCompat.Builder(context, CANAL_ID)
        notificacion.setSmallIcon(R.drawable.ic_android)
        notificacion.setLargeIcon(BitmapFactory.decodeResource(context.resources, R.drawable.ic_android))
        notificacion.setContentTitle("Titulo Notificacion")
        notificacion.setContentText("Aca colocamos en contenido de la notificacion")
        notificacion.setSubText("Mi notificacion")

        val v = longArrayOf(0, 800, 100, 100, 200, 100)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            var vibrator = context.getSystemService(VIBRATOR_SERVICE) as Vibrator
            vibrator.vibrate(VibrationEffect.createWaveform(v, -1))
            playNotificationSound()
        } else {
            notificacion.setVibrate(v)
            val alarmaSonido = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            notificacion.setSound(alarmaSonido)
        }

        val intent = Intent(context, ResultadoActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)
        notificacion.setContentIntent(pendingIntent)

        notificacion.setOngoing(true)
        notificacion.setAutoCancel(true)

        mNotificationManager.notify(NOTIFICATION_ID, notificacion.build())
    }

    fun playNotificationSound() {
        try {
            //val alarmaSonido = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val alarmaSonido = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + applicationContext.packageName + "/"+ R.raw.iphone_notificacion)
            val r = RingtoneManager.getRingtone(applicationContext, alarmaSonido)
            r.play()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun createExpandableNotification(context: Context) {
        val notificacion = NotificationCompat.Builder(context, CANAL_ID)
        notificacion.setSmallIcon(R.drawable.ic_android)
        notificacion.setLargeIcon(BitmapFactory.decodeResource(context.resources, R.drawable.ic_android))
        notificacion.setContentTitle("Expandible")
        notificacion.setContentText("Aca colocamos en contenido de la notificacion")

        val lorem = context.resources.getString(R.string.long_lorem)

        val inboxStyle = NotificationCompat.InboxStyle()
        val content = lorem.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        for (line in content) {
            inboxStyle.addLine(line)
        }
        inboxStyle.setBigContentTitle("Titulo expandido")
        notificacion.setStyle(inboxStyle)
        mNotificationManager.notify(NOTIFICATION_ID, notificacion.build())
    }

    private fun createBigImageNotification(context: Context) {
        val notificacion = NotificationCompat.Builder(context, CANAL_ID)
        notificacion.setSmallIcon(R.drawable.ic_android)
        notificacion.setLargeIcon(BitmapFactory.decodeResource(context.resources, R.drawable.ic_android))
        notificacion.setContentTitle("Expandible")
        notificacion.setContentText("Aca colocamos en contenido de la notificacion")

        val bigPictureStyle = NotificationCompat.BigPictureStyle()
        bigPictureStyle.bigPicture(BitmapFactory.decodeResource(resources, R.drawable.banner))
        notificacion.setStyle(bigPictureStyle)
        mNotificationManager.notify(NOTIFICATION_ID, notificacion.build())
    }

    private fun createButtonNotification(context: Context) {
        val notificacion = NotificationCompat.Builder(context, CANAL_ID)
        notificacion.setSmallIcon(R.drawable.ic_android)
        notificacion.setLargeIcon(BitmapFactory.decodeResource(context.resources, R.drawable.ic_android))
        notificacion.setContentTitle("Botones")
        notificacion.setContentText("Aca colocamos en contenido de la notificacion")

        val intent = Intent(context, ResultadoActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)
        notificacion.setContentIntent(pendingIntent)

        notificacion.addAction(R.drawable.ic_android, "Responder", pendingIntent)
        notificacion.addAction(R.drawable.ic_android, "Ignorar", pendingIntent)
        sendBroadcast(Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS))

        mNotificationManager.notify(NOTIFICATION_ID, notificacion.build())
    }

}