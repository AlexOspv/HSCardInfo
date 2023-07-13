package com.alexos.heartstonecardsinfo.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.alexos.heartstonecardsinfo.data.network.mapper.CardMapper
import com.alexos.heartstonecardsinfo.domain.GetCardsInfoListUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel(mapper: CardMapper):ViewModel() {

    private val getCardsInfoListUseCaseUseCase = GetCardsInfoListUseCase(mapper)
    private var myCompositeDisposable: CompositeDisposable? = null


    init {
        myCompositeDisposable = CompositeDisposable()
    }

    fun getCarsList() {
        myCompositeDisposable?.addAll(
            getCardsInfoListUseCaseUseCase.getCardInfoList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(

                )
        )
    }
}