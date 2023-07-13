package com.alexos.heartstonecardsinfo.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.alexos.heartstonecardsinfo.R
import com.alexos.heartstonecardsinfo.domain.CardInfo
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

class CardsListAdapter : ListAdapter<CardInfo, CardItemViewHolder>(CardItemDiffCallback()) {

    var onCardItemClickListener: ((CardInfo) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card_info, parent, false)
        return CardItemViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: CardItemViewHolder, position: Int) {
        val cardInfoItem = getItem(position)
        viewHolder.view.setOnClickListener {
            onCardItemClickListener?.invoke(cardInfoItem)
        }
        viewHolder.tvName.text = cardInfoItem.name
        Glide.with(viewHolder.ivPhoto.context).load(cardInfoItem.img)
            .placeholder(R.drawable.no_image_svgrepo_com)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .apply(RequestOptions().skipMemoryCache(false))
            .fitCenter()
            .into(viewHolder.ivPhoto)
    }
}