package com.alexos.heartstonecardsinfo.domain

import androidx.lifecycle.LiveData
import com.alexos.heartstonecardsinfo.data.network.ApiFactory
import com.alexos.heartstonecardsinfo.data.network.mapper.CardMapper
import io.reactivex.Single

class GetCardsInfoListUseCase(private val mapper: CardMapper) {

    fun getCardInfoList(): Single<CardInfo> {
        return ApiFactory.create().getCardsInfoList()
            .map { mapper.mapDtoToModel(it)}
    }
}