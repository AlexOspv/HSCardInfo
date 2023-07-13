package com.alexos.heartstonecardsinfo.presentation.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alexos.heartstonecardsinfo.R

class CardItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    val tvName = view.findViewById<TextView>(R.id.tv_name)
    val ivPhoto = view.findViewById<ImageView>(R.id.iv_photo)
}