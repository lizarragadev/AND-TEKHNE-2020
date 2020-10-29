package com.miramicodigo.pickers

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.miramicodigo.pickers.view.DatePickDialog
import com.miramicodigo.pickers.view.TimePickDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()  {

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

    }

    private fun showTimePickerDialog() {

    }

}
