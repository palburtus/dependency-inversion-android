package com.example.dependencyinversion.ui

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dependencyinversion.R

class NewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val textViewTitle: TextView = this.itemView.findViewById(R.id.text_view_title)

    fun bind(title: String){
        textViewTitle.text = title
    }

}