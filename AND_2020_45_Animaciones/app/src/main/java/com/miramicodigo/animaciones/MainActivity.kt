package com.miramicodigo.animaciones

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var animacion: Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnZoomin.setOnClickListener { zoomin() }
        btnZoomout.setOnClickListener { zoomout() }
        btnFadein.setOnClickListener { fadein() }
        btnFadeout.setOnClickListener { fadeout() }
        btnRotate.setOnClickListener { rotate() }
        btnMove.setOnClickListener { move() }
        btnSlideUp.setOnClickListener { slideup() }
        btnSlidedown.setOnClickListener { slidedown() }
        btnBlink.setOnClickListener { blink() }
        btnBounce.setOnClickListener { bounce() }
        btnCrossfade.setOnClickListener { crossfade() }
        btnSequential.setOnClickListener { sequential() }
        btnTogether.setOnClickListener { together() }
        btnStop.setOnClickListener { stop() }
    }

    fun zoomin() {
        animacion = AnimationUtils.loadAnimation(this, R.anim.zoom_in)
        ivOctocat.startAnimation(animacion)
    }

    fun zoomout() {
        animacion = AnimationUtils.loadAnimation(this, R.anim.zoom_out)
        ivOctocat.startAnimation(animacion)
    }

    fun fadein() {
        animacion = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        ivOctocat.startAnimation(animacion)
    }

    fun fadeout() {
        animacion = AnimationUtils.loadAnimation(this, R.anim.fade_out)
        ivOctocat.startAnimation(animacion)
    }

    fun rotate() {
        animacion = AnimationUtils.loadAnimation(this, R.anim.rotate)
        ivOctocat.startAnimation(animacion)
    }

    fun move() {
        animacion = AnimationUtils.loadAnimation(this, R.anim.move)
        ivOctocat.startAnimation(animacion)
    }

    fun slideup() {
        animacion = AnimationUtils.loadAnimation(this, R.anim.slide_up)
        ivOctocat.startAnimation(animacion)
    }

    fun slidedown() {
        animacion = AnimationUtils.loadAnimation(this, R.anim.slide_down)
        ivOctocat.startAnimation(animacion)
    }

    fun blink() {
        animacion = AnimationUtils.loadAnimation(this, R.anim.blink)
        ivOctocat.startAnimation(animacion)
    }

    fun bounce() {
        animacion = AnimationUtils.loadAnimation(this, R.anim.bounce)
        ivOctocat.startAnimation(animacion)
    }

    fun crossfade() {
        animacion = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        val animacion2 = AnimationUtils.loadAnimation(this, R.anim.fade_out)
        ivOctocat.startAnimation(animacion2)
        ivOctocat2.startAnimation(animacion)
    }

    fun sequential() {
        animacion = AnimationUtils.loadAnimation(this, R.anim.sequential)
        ivOctocat.startAnimation(animacion)
    }

    fun together() {
        animacion = AnimationUtils.loadAnimation(this, R.anim.together)
        ivOctocat.startAnimation(animacion)
    }

    fun stop() {
        ivOctocat2.clearAnimation()
        ivOctocat.clearAnimation()
    }
}