package com.alexos.heartstonecardsinfo.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alexos.heartstonecardsinfo.data.network.mapper.CardMapper
import com.alexos.heartstonecardsinfo.domain.CardInfo
import com.alexos.heartstonecardsinfo.domain.GetCardsInfoListUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel():ViewModel() {

    private val mapper = CardMapper
    private val getCardsInfoListUseCaseUseCase = GetCardsInfoListUseCase(mapper)
    private var myCompositeDisposable: CompositeDisposable? = null
    private val _cardsInfoList: MutableLiveData<ArrayList<CardInfo>> = MutableLiveData()
    val cardsInfoList: LiveData<ArrayList<CardInfo>> = _cardsInfoList

    init {
        myCompositeDisposable = CompositeDisposable()
    }

    fun getCardsList() {
        myCompositeDisposable?.addAll(
            getCardsInfoListUseCaseUseCase.getCardInfoList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handlerResponseCardsInfo, this::handleErrorCardsInfo)
        )
    }

    private fun handlerResponseCardsInfo(response: ArrayList<CardInfo>) {
        var prevName = ""
        val cardList = arrayListOf<CardInfo>()
        for (i in response.indices){
            if (response[i].name != prevName && response[i].img != null){
                cardList.add(response[i])
            }
            prevName = response[i].name.toString()
        }
        _cardsInfoList.value = cardList
        Log.d("MainViewModelImpl", "handlerResponseCardsInfo: $_cardsInfoList")
    }

    private fun handleErrorCardsInfo(err: Throwable) {
        Log.d("MainViewModelImpl", "handleErrorCardsInfo: $err")
    }

    override fun onCleared() {
        super.onCleared()
        myCompositeDisposable?.clear()
    }
}