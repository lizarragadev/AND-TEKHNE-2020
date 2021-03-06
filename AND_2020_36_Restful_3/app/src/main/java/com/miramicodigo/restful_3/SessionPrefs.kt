package com.miramicodigo.restful_3

import android.content.Context
import android.content.SharedPreferences
import android.text.TextUtils
import com.miramicodigo.restful_3.model.ResponsePersonaLogin

class SessionPrefs constructor(context: Context) {

    private val PREFERENCES_NAME = "PERSONA_PREFERENCES"
    private val PREFERENCES_PERSONA_ID = "PREF_PERSONA_USER_ID"
    private val PREFERENCES_PERSONA_NAME = "PREF_PERSONA_NAME"
    private val PREFERENCES_PERSONA_ADDRESS = "PREF_PERSONA_ADDRESS"
    private val PREFERENCES_PERSONA_GENDER = "PREF_PERSONA_GENDER"
    private val PREFERENCES_PERSONA_TOKEN = "PREF_PERSONA_TOKEN"

    var mPrefs: SharedPreferences

    private var mIsLoggedIn = false

    lateinit var INSTANCE: SessionPrefs

    fun get(cont: Context): SessionPrefs {
        INSTANCE = SessionPrefs(cont)
        return INSTANCE
    }

    init {
        mPrefs = context.applicationContext.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
        mIsLoggedIn = !TextUtils.isEmpty(mPrefs.getString(PREFERENCES_PERSONA_TOKEN, null))
    }

    fun estaLogueado(): Boolean {
        return mIsLoggedIn
    }

    fun guardarUsuario(responsePersonaLogin: ResponsePersonaLogin) {
        if(responsePersonaLogin != null) {
            val editor = mPrefs.edit()
            editor.putString(PREFERENCES_PERSONA_ID, responsePersonaLogin.id)
            editor.putString(PREFERENCES_PERSONA_NAME, responsePersonaLogin.name)
            editor.putString(PREFERENCES_PERSONA_ADDRESS, responsePersonaLogin.address)
            editor.putString(PREFERENCES_PERSONA_GENDER, responsePersonaLogin.gender)
            editor.putString(PREFERENCES_PERSONA_TOKEN, responsePersonaLogin.token)
            editor.apply()
            mIsLoggedIn = true
        }
    }

    fun cerrarSesion(){
        mIsLoggedIn = false
        val editor = mPrefs.edit()
        editor.putString(PREFERENCES_PERSONA_ID, null)
        editor.putString(PREFERENCES_PERSONA_NAME, null)
        editor.putString(PREFERENCES_PERSONA_ADDRESS, null)
        editor.putString(PREFERENCES_PERSONA_GENDER, null)
        editor.putString(PREFERENCES_PERSONA_TOKEN, null)
        editor.apply()
    }

    fun getPersona() : ResponsePersonaLogin {
        val id = mPrefs.getString(PREFERENCES_PERSONA_ID, "")
        val name = mPrefs.getString(PREFERENCES_PERSONA_NAME, "")
        val address = mPrefs.getString(PREFERENCES_PERSONA_ADDRESS, "")
        val gender = mPrefs.getString(PREFERENCES_PERSONA_GENDER, "")
        val token = mPrefs.getString(PREFERENCES_PERSONA_TOKEN, "")

        return ResponsePersonaLogin(id, name, address, gender, token)
    }
}
