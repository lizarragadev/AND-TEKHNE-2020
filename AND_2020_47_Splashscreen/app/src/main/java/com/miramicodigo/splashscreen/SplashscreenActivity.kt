package com.miramicodigo.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_splashscreen.*

class SplashscreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)
        supportActionBar?.hide()

        val animacion = AnimationUtils.loadAnimation(this, R.anim.animacion)
        ivLogo.startAnimation(animacion)

        animacion.setAnimationListener(object: Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {

            }

            override fun onAnimationEnd(p0: Animation?) {
                startActivity(Intent(applicationContext, MainActivity::class.java))
                finish()
            }

            override fun onAnimationRepeat(p0: Animation?) {

            }
        })
    }
}