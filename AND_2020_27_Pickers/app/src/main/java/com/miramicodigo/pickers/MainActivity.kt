package com.miramicodigo.pickers

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() , DateTimeInterface {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ibFecha.setOnClickListener {
            showDatePickerDialog()
        }

        ibHora.setOnClickListener {
            showTimePickerDialog()
        }
    }

    private fun showDatePickerDialog() {
        val dialogFragment = DatePickerDialog()
        dialogFragment.show(supportFragmentManager, "datePicker")
    }

    private fun showTimePickerDialog() {
        val dialogFragment = TimePickerDialog()
        dialogFragment.show(supportFragmentManager, "timePicker")
    }

    override fun obtieneFecha(date: String) {
        etFecha.setText(date)
    }

    override fun obtieneHora(hour: String) {
        etHora.setText(hour)
    }

}
