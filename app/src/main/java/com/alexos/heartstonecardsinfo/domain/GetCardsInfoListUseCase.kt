package com.alexos.heartstonecardsinfo.domain

import android.util.Log
import androidx.lifecycle.LiveData
import com.alexos.heartstonecardsinfo.data.network.ApiFactory
import com.alexos.heartstonecardsinfo.data.network.mapper.CardMapper
import io.reactivex.Single

class GetCardsInfoListUseCase(private val mapper: CardMapper) {

    fun getCardInfoList(): Single<ArrayList<CardInfo>> {
        val response = ApiFactory.create().getCardsInfoList()
            .map { mapper.mapDtoToModel(it) }
        Log.d("GetCardsInfoListUseCase", "getCardInfoList: $response")
        return response
    }
}