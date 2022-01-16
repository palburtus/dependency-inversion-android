package com.example.dependencyinversion

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class NewsAdapter(private val items: Array<String>) : RecyclerView.Adapter<NewViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): NewViewHolder {

        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.news_viewholder, viewGroup, false)
        return NewViewHolder(view);
    }

    override fun onBindViewHolder(viewHolder: NewViewHolder, position: Int) {
        viewHolder.textViewTitle.text = this.items[position];
    }

    override fun getItemCount(): Int {
        return items.size;
    }
}