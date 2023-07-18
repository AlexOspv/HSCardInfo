package com.alexos.heartstonecardsinfo.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alexos.heartstonecardsinfo.data.network.mapper.CardMapper
import com.alexos.heartstonecardsinfo.domain.CardInfo
import com.alexos.heartstonecardsinfo.domain.CardsListRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

object CardsListRepositoryImpl: CardsListRepository {

    private var myCompositeDisposable: CompositeDisposable? = null
    private val mapper = CardMapper

    private val cardsListLD = MutableLiveData<List<CardInfo>>()
    private var cardsList = mutableListOf<CardInfo>()

    init {
        myCompositeDisposable = CompositeDisposable()
    }

    override fun getCardsInfoList(): LiveData<List<CardInfo>> {
        myCompositeDisposable?.addAll(
            ApiFactory.create().getCardsInfoList()
                .map { mapper.mapDtoToModel(it) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponseCardsInfo, this::handleErrorCardsInfo)
        )
        return cardsListLD
    }

    private fun handleResponseCardsInfo(response: List<CardInfo>) {
        var prevName = ""
        val cardListResponse = arrayListOf<CardInfo>()
        for (i in response.indices) {
            if (response[i].name != prevName && response[i].img != null) {
                cardListResponse.add(response[i])
            }
            prevName = response[i].name.toString()
        }
        cardsList = cardListResponse
        updateList()
        Log.d("CardsListRepositoryImpl", "handlerResponseCardsInfo: $cardsListLD")
    }

    private fun handleErrorCardsInfo(err: Throwable) {
        Log.d("CardsListRepositoryImpl", "handleErrorCardsInfo: $err")
        throw RuntimeException("Check your network and try again")
    }

    override fun getCardInfo(cardInfoId: Int): CardInfo {
        return cardsList.find {
            it.dbfId == cardInfoId
        } ?: throw RuntimeException("Element with id $cardInfoId not found")
    }

    private fun updateList() {
        cardsListLD.value = cardsList
    }

    fun clearDisposables() {
        myCompositeDisposable?.clear()
    }
}