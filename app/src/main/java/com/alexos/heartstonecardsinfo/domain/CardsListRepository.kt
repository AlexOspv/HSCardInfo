package com.alexos.heartstonecardsinfo.domain

import androidx.lifecycle.LiveData

interface CardsListRepository {

    fun getCardsInfoList() : LiveData<List<CardInfo>>

    fun getCardInfo(cardInfoId: Int): CardInfo
}