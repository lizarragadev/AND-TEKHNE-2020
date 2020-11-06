package com.miramicodigo.sqlite.db

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.Context
import android.database.Cursor
import com.miramicodigo.sqlite.Constants
import com.miramicodigo.sqlite.model.Persona

class DatabaseAdapter(context: Context) {

    private val databaseHelper: PersonasDatabaseHelper
    lateinit var db: SQLiteDatabase

    init {
        databaseHelper = PersonasDatabaseHelper(context)
    }

    fun abrir() {

    }

    fun cerrar() {

    }

    fun adicionarPersona(persona: Persona): Long {

        return null!!
    }

    fun actualizarPersona(persona: Persona): Int {

        return null!!
    }

    fun eliminarPersona(id: Int): Boolean {

        return false
    }

    fun obtenerPersona(id: Int): Cursor {

        return null!!
    }

    fun obtenerTodasPersonas(): Cursor {

        return null!!
    }

    private class PersonasDatabaseHelper(context: Context) : SQLiteOpenHelper(context, "dbpersonas.db", null, 1) {

        override fun onCreate(db: SQLiteDatabase) {

        }

        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

        }
    }
}
