package com.miramicodigo.listaspersonalizadas

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter

class CustomAdapter(activity: Activity) : BaseAdapter() {

    private val context: Context

    init {
        this.context = activity
    }

    override fun getCount(): Int {
        return 0
    }

    override fun getItem(position: Int): Any {
        return 0
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {


        return convertView
    }

    class ViewHolder(view: View) {

    }

}