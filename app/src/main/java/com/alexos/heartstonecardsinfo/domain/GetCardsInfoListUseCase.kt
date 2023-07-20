package com.alexos.heartstonecardsinfo.domain

import androidx.lifecycle.LiveData
import com.alexos.heartstonecardsinfo.data.repository.CardsListRepositoryImpl

class GetCardsInfoListUseCase(
    private val cardsListRepositoryImpl: CardsListRepositoryImpl
) {

    fun getCardInfoList(): LiveData<List<CardInfo>> {
        return cardsListRepositoryImpl.getCardsInfoList()
    }

}