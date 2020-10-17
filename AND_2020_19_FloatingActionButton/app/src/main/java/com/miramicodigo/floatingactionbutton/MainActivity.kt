package com.miramicodigo.floatingactionbutton

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fab_layout.*

class MainActivity : AppCompatActivity() {

    var STATUS = false

    lateinit var show_fab_1: Animation
    lateinit var show_fab_2: Animation
    lateinit var show_fab_3: Animation
    lateinit var hide_fab_1: Animation
    lateinit var hide_fab_2: Animation
    lateinit var hide_fab_3: Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        show_fab_1 = AnimationUtils.loadAnimation(applicationContext, R.anim.fab1_show)
        show_fab_2 = AnimationUtils.loadAnimation(applicationContext, R.anim.fab2_show)
        show_fab_3 = AnimationUtils.loadAnimation(applicationContext, R.anim.fab3_show)
        hide_fab_1 = AnimationUtils.loadAnimation(applicationContext, R.anim.fab1_hide)
        hide_fab_2= AnimationUtils.loadAnimation(applicationContext, R.anim.fab2_hide)
        hide_fab_3 = AnimationUtils.loadAnimation(applicationContext, R.anim.fab3_hide)

        fabMain.setOnClickListener {
            STATUS = if(!STATUS) {
                expandFAB()
                true
            } else {
                hideFAB()
                false
            }
        }

        fab_1.setOnClickListener {
            Toast.makeText(applicationContext, "Click en FAB 1", Toast.LENGTH_SHORT).show()
        }

        fab_2.setOnClickListener {
            Toast.makeText(applicationContext, "Click en FAB 2", Toast.LENGTH_SHORT).show()
        }

        fab_3.setOnClickListener {
            Toast.makeText(applicationContext, "Click en FAB 3", Toast.LENGTH_SHORT).show()
        }

    }

    private fun expandFAB() {
        val layoutParams = fab_1.layoutParams as FrameLayout.LayoutParams
        layoutParams.rightMargin += (fab_1.width * 1.7).toInt()
        layoutParams.bottomMargin += (fab_1.height * 0.25).toInt()
        fab_1.layoutParams = layoutParams
        fab_1.startAnimation(show_fab_1)
        fab_1.isClickable = true

        val layoutParams2 = fab_2.layoutParams as FrameLayout.LayoutParams
        layoutParams2.rightMargin += (fab_2.width * 1.5).toInt()
        layoutParams2.bottomMargin += (fab_2.height * 1.5).toInt()
        fab_2.layoutParams = layoutParams2
        fab_2.startAnimation(show_fab_2)
        fab_2.isClickable = true

        val layoutParams3 = fab_3.layoutParams as FrameLayout.LayoutParams
        layoutParams3.rightMargin += (fab_3.width * 0.25).toInt()
        layoutParams3.bottomMargin += (fab_3.height * 1.7).toInt()
        fab_3.layoutParams = layoutParams3
        fab_3.startAnimation(show_fab_3)
        fab_3.isClickable = true
    }

    private fun hideFAB() {
        val layoutParams = fab_1.layoutParams as FrameLayout.LayoutParams
        layoutParams.rightMargin -= (fab_1.width * 1.7).toInt()
        layoutParams.bottomMargin -= (fab_1.height * 0.25).toInt()
        fab_1.layoutParams = layoutParams
        fab_1.startAnimation(hide_fab_1)
        fab_1.isClickable = false

        val layoutParams2 = fab_2.layoutParams as FrameLayout.LayoutParams
        layoutParams2.rightMargin -= (fab_2.width * 1.5).toInt()
        layoutParams2.bottomMargin -= (fab_2.height * 1.5).toInt()
        fab_2.layoutParams = layoutParams2
        fab_2.startAnimation(hide_fab_2)
        fab_2.isClickable = false

        val layoutParams3 = fab_3.layoutParams as FrameLayout.LayoutParams
        layoutParams3.rightMargin -= (fab_3.width * 0.25).toInt()
        layoutParams3.bottomMargin -= (fab_3.height * 1.7).toInt()
        fab_3.layoutParams = layoutParams3
        fab_3.startAnimation(hide_fab_3)
        fab_3.isClickable = false
    }
}