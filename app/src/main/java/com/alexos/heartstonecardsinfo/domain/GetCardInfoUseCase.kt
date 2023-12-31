package com.alexos.heartstonecardsinfo.domain

import com.alexos.heartstonecardsinfo.data.repository.CardsListRepositoryImpl

class GetCardInfoUseCase(
    private val cardsListRepositoryImpl: CardsListRepositoryImpl
) {

    fun getCardInfo(cardInfoId: Int): CardInfo {
        return cardsListRepositoryImpl.getCardInfo(cardInfoId)
    }
}