package com.alexos.heartstonecardsinfo.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.alexos.heartstonecardsinfo.R
import com.alexos.heartstonecardsinfo.databinding.ItemCardInfoBinding
import com.alexos.heartstonecardsinfo.domain.CardInfo
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

class CardsListAdapter : ListAdapter<CardInfo, CardItemViewHolder>(CardItemDiffCallback()) {

    var onCardItemClickListener: ((CardInfo) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardItemViewHolder {
        val binding = ItemCardInfoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CardItemViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: CardItemViewHolder, position: Int) {
        val cardInfoItem = getItem(position)
        val binding = viewHolder.binding
        binding.root.setOnClickListener {
            onCardItemClickListener?.invoke(cardInfoItem)
        }
        binding.tvName.text = cardInfoItem.name
        Glide.with(binding.ivPhoto.context).load(cardInfoItem.img)
            .placeholder(R.drawable.no_image_svgrepo_com)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .apply(RequestOptions().skipMemoryCache(false))
            .fitCenter()
            .into(binding.ivPhoto)
    }
}