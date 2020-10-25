package com.miramicodigo.pickers

import android.app.Dialog
import android.widget.TimePicker
import android.app.TimePickerDialog
import android.text.format.DateFormat
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import java.util.*

class TimePickerDialog : DialogFragment(), TimePickerDialog.OnTimeSetListener {

    var timePickerDialog: TimePickerDialog? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)

        timePickerDialog = TimePickerDialog(activity, this,
                hour, minute, DateFormat.is24HourFormat(activity))

        return timePickerDialog as TimePickerDialog
    }

    override fun onTimeSet(timePicker: TimePicker, hora: Int, minuto: Int) {
        val horaFormateada = if (hora < 10) "0$hora" else hora.toString()
        val minutoFormateado = if (minuto < 10) "0$minuto" else minuto.toString()
        val AM_PM: String
        if (hora < 12) {
            AM_PM = "a.m."
        } else {
            AM_PM = "p.m."
        }
        val resultado = "$horaFormateada:$minutoFormateado $AM_PM"

        (activity as DateTimeInterface).obtieneHora(resultado)
    }


}
