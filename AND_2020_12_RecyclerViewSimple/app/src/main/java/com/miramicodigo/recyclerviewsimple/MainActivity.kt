package com.miramicodigo.recyclerviewsimple

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }


    inner class RecyclerViewCustomHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    inner class RecyclerViewAdapter(val data: ArrayList<String>) : RecyclerView.Adapter<RecyclerViewCustomHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewCustomHolder {
            val layoutInflater = LayoutInflater.from(applicationContext)
            val view = layoutInflater.inflate(android.R.layout.simple_list_item_1, parent, false)
            return RecyclerViewCustomHolder(view)
        }

        override fun onBindViewHolder(holder: RecyclerViewCustomHolder, position: Int) {

        }

        override fun getItemCount(): Int {
            return data.size
        }
    }
}