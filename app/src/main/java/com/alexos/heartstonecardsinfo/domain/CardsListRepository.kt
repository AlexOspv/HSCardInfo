package com.alexos.heartstonecardsinfo.domain

import androidx.lifecycle.LiveData
import io.reactivex.Single
import java.util.ArrayList

interface CardsListRepository {

    fun getCardsInfoList() : LiveData<List<CardInfo>>

    fun getCardInfo(cardInfoId: Int): CardInfo
}