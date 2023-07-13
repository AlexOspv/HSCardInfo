package com.alexos.heartstonecardsinfo.domain

import androidx.lifecycle.LiveData
import io.reactivex.Single

interface CardRepository {

    fun getCardInfoList(): Single<CardInfo>

    fun getCardInfo(cardInfoId: Int): CardInfo

}