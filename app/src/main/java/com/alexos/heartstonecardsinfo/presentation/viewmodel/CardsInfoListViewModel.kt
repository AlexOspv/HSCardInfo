package com.alexos.heartstonecardsinfo.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alexos.heartstonecardsinfo.data.network.CardsListRepositoryImpl
import com.alexos.heartstonecardsinfo.domain.CardInfo
import com.alexos.heartstonecardsinfo.domain.GetCardsInfoListUseCase

class CardsInfoListViewModel:ViewModel() {

    private val repository = CardsListRepositoryImpl
    private val getCardsInfoListUseCaseUseCase = GetCardsInfoListUseCase(repository)
    val cardsInfoList: LiveData<List<CardInfo>> = getCardsInfoListUseCaseUseCase.getCardInfoList()

    override fun onCleared() {
        super.onCleared()
        repository.clearDisposables()
    }
}