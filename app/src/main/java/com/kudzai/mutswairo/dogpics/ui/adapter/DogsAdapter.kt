package com.kudzai.mutswairo.dogpics.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.kudzai.mutswairo.dogpics.R
import com.squareup.picasso.Picasso

class DogsAdapter(private val data : List<String>) : RecyclerView.Adapter<DogsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogsAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_image, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: DogsAdapter.ViewHolder, position: Int) {
        val item = data[position]

        Picasso.get().load("https://i.imgur.com/DvpvklR.png").into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val imageView = itemView.findViewById<ImageView>(R.id.ivImageItem)
    }
}