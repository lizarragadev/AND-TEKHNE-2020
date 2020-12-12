package com.miramicodigo.mediaplayer

import android.media.MediaPlayer
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    lateinit var animacion: Animation
    lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)

        animacion = AnimationUtils.loadAnimation(this, R.anim.blink)

        initUI()
        initMediaPLayer()

        btnPlay.setOnClickListener { play() }
        btnPause.setOnClickListener { pause() }
        btnStop.setOnClickListener { stop() }
    }

    fun initUI() {
        btnPlay.isEnabled = true
        btnPause.isEnabled = false
        btnStop.isEnabled = false
    }

    fun initMediaPLayer() {
        mediaPlayer = MediaPlayer.create(this, R.raw.acdc)
    }

    fun play() {
        try {
            mediaPlayer.start()
            ivAlpha.startAnimation(animacion)
            btnPause.isEnabled = true
            btnStop.isEnabled = true
            btnPlay.isEnabled = false
        } catch (e: Exception) { }
    }

    fun pause() {
        if(mediaPlayer.isPlaying) {
            mediaPlayer.pause()
            ivAlpha.clearAnimation()
            btnPause.isEnabled = false
            btnPlay.isEnabled = true
            btnStop.isEnabled = true
        }
    }

    fun stop() {
        if(mediaPlayer.isPlaying) {
            mediaPlayer.stop()
            mediaPlayer.release()
            ivAlpha.clearAnimation()
            initMediaPLayer()
            initUI()
        }
    }

}