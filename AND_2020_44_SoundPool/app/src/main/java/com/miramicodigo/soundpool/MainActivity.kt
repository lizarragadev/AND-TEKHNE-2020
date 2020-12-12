package com.miramicodigo.soundpool

import android.media.AudioAttributes
import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var spIdiota: SoundPool
    lateinit var spCallate: SoundPool
    lateinit var spVeteAlDiablo: SoundPool

    var resIdiota = 0
    var resCallate = 0
    var resVeteAlDiablo = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        createSoundPool()

        ivIdiota.setOnClickListener { idiota() }
        ivCallate.setOnClickListener { callate() }
        ivVeteAlDiablo.setOnClickListener { veteAlDiablo() }
    }

    private fun createSoundPool() {
        val attributes = AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION).build()
        spIdiota = SoundPool.Builder().setAudioAttributes(attributes).build()
        spCallate = SoundPool.Builder().setAudioAttributes(attributes).build()
        spVeteAlDiablo = SoundPool.Builder().setAudioAttributes(attributes).build()
        loadSoundPool()
    }

    private fun loadSoundPool() {
        resIdiota = spIdiota.load(this, R.raw.idiota, 1)
        resCallate = spCallate.load(this, R.raw.callate, 1)
        resVeteAlDiablo = spVeteAlDiablo.load(this, R.raw.vete_al_diablo, 1)
    }

    private fun idiota() {
        if(resIdiota != 0) {
            spIdiota.play(resIdiota, 1.0f, 1.0f, 0, 0, 0f)
        }
    }

    private fun callate() {
        if(resCallate != 0) {
            spCallate.play(resCallate, 1.0f, 1.0f, 0, 0, 0f)
        }
    }

    private fun veteAlDiablo() {
        if(resVeteAlDiablo != 0) {
            spVeteAlDiablo.play(resVeteAlDiablo, 1.0f, 1.0f, 0, 0, 0f)
        }
    }

}