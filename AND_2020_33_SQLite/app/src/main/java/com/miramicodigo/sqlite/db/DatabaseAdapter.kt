package com.miramicodigo.sqlite.db

import android.content.ContentValues
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
        db = databaseHelper.writableDatabase
    }

    fun cerrar() {
        databaseHelper.close()
    }

    fun adicionarPersona(persona: Persona): Long {
        val contentValues = ContentValues()
        contentValues.put(Constants.NOMBRE, persona.nombre)
        contentValues.put(Constants.TELEFONO, persona.telefono)
        contentValues.put(Constants.CORREO, persona.correo)
        contentValues.put(Constants.GENERO, persona.genero)

        return db.insert(Constants.TABLA_PERSONA, null, contentValues)
    }

    fun actualizarPersona(persona: Persona): Int {
        val contentValues = ContentValues()
        contentValues.put(Constants.NOMBRE, persona.nombre)
        contentValues.put(Constants.TELEFONO, persona.telefono)
        contentValues.put(Constants.CORREO, persona.correo)
        contentValues.put(Constants.GENERO, persona.genero)

        return db.update(Constants.TABLA_PERSONA, contentValues, "${Constants.ID}=?", arrayOf(persona.id.toString()))
    }

    fun eliminarPersona(id: Int): Boolean {
        return db.delete(Constants.TABLA_PERSONA, "${Constants.ID}=$id", null) > 0
    }

    fun obtenerPersona(id: Int): Cursor {
        // select * from persona where id=$id
        return db.query(Constants.TABLA_PERSONA, arrayOf(Constants.ID, Constants.NOMBRE, Constants.TELEFONO, Constants.CORREO, Constants.GENERO),
            "${Constants.ID}=?", arrayOf(id.toString()), null, null, null)
    }

    fun obtenerTodasPersonas(): Cursor {
        // select * from persona
        return db.query(Constants.TABLA_PERSONA, arrayOf(Constants.ID, Constants.NOMBRE, Constants.TELEFONO, Constants.CORREO, Constants.GENERO),
            null, null, null, null, "${Constants.NOMBRE} asc")
    }

    private class PersonasDatabaseHelper(context: Context) : SQLiteOpenHelper(context, "dbpersonas.db", null, 1) {

        override fun onCreate(db: SQLiteDatabase) {
            db.execSQL("CREATE TABLE ${Constants.TABLA_PERSONA} (" +
                    "${Constants.ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "${Constants.NOMBRE} TEXT NOT NULL," +
                    "${Constants.TELEFONO} TEXT NOT NULL," +
                    "${Constants.CORREO} TEXT NOT NULL," +
                    "${Constants.GENERO} TEXT NOT NULL" +
                    ");")
        }

        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
            db.execSQL("DROP TABLE IF EXISTS ${Constants.TABLA_PERSONA}")
            onCreate(db)
        }
    }
}
