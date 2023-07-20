package com.alexos.heartstonecardsinfo.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alexos.heartstonecardsinfo.data.repository.CardsListRepositoryImpl
import com.alexos.heartstonecardsinfo.domain.CardInfo
import com.alexos.heartstonecardsinfo.domain.GetCardInfoUseCase

class CardInfoViewModel: ViewModel() {

    private val repository = CardsListRepositoryImpl
    private val getCardInfoUseCase = GetCardInfoUseCase(repository)

    private val _cardInfoItem = MutableLiveData<CardInfo>()
    val cardInfoItem: LiveData<CardInfo>
        get() = _cardInfoItem

    fun getCardInfoItem(cardInfoItemId: Int) {
        val item = getCardInfoUseCase.getCardInfo(cardInfoItemId)
        _cardInfoItem.value = item
    }

}