package com.miramicodigo.floatingactionbutton

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.fab_layout.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    private fun expandFAB() {
        val layoutParams = fab_1.layoutParams as FrameLayout.LayoutParams
        layoutParams.rightMargin += (fab_1.width * 1.7).toInt()
        layoutParams.bottomMargin += (fab_1.height * 0.25).toInt()
        fab_1.layoutParams = layoutParams
        //fab_1.startAnimation(show_fab_1)
        fab_1.isClickable = true

        val layoutParams2 = fab_2.layoutParams as FrameLayout.LayoutParams
        layoutParams2.rightMargin += (fab_2.width * 1.5).toInt()
        layoutParams2.bottomMargin += (fab_2.height * 1.5).toInt()
        fab_2.layoutParams = layoutParams2
        //fab_2.startAnimation(show_fab_2)
        fab_2.isClickable = true

        val layoutParams3 = fab_3.layoutParams as FrameLayout.LayoutParams
        layoutParams3.rightMargin += (fab_3.width * 0.25).toInt()
        layoutParams3.bottomMargin += (fab_3.height * 1.7).toInt()
        fab_3.layoutParams = layoutParams3
        //fab_3.startAnimation(show_fab_3)
        fab_3.isClickable = true
    }

    private fun hideFAB() {
        val layoutParams = fab_1.layoutParams as FrameLayout.LayoutParams
        layoutParams.rightMargin -= (fab_1.width * 1.7).toInt()
        layoutParams.bottomMargin -= (fab_1.height * 0.25).toInt()
        fab_1.layoutParams = layoutParams
        //fab_1.startAnimation(hide_fab_1)
        fab_1.isClickable = false

        val layoutParams2 = fab_2.layoutParams as FrameLayout.LayoutParams
        layoutParams2.rightMargin -= (fab_2.width * 1.5).toInt()
        layoutParams2.bottomMargin -= (fab_2.height * 1.5).toInt()
        fab_2.layoutParams = layoutParams2
        //fab_2.startAnimation(hide_fab_2)
        fab_2.isClickable = false

        val layoutParams3 = fab_3.layoutParams as FrameLayout.LayoutParams
        layoutParams3.rightMargin -= (fab_3.width * 0.25).toInt()
        layoutParams3.bottomMargin -= (fab_3.height * 1.7).toInt()
        fab_3.layoutParams = layoutParams3
        //fab_3.startAnimation(hide_fab_3)
        fab_3.isClickable = false
    }
}