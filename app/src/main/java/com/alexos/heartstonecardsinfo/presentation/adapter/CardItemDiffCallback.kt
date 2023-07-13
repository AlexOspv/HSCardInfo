package com.alexos.heartstonecardsinfo.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.alexos.heartstonecardsinfo.domain.CardInfo

class CardItemDiffCallback: DiffUtil.ItemCallback<CardInfo>() {

    override fun areItemsTheSame(oldItem: CardInfo, newItem: CardInfo): Boolean {
        return oldItem.dbfId == newItem.dbfId
    }

    override fun areContentsTheSame(oldItem: CardInfo, newItem: CardInfo): Boolean {
        return oldItem == newItem
    }
}