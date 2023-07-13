package com.alexos.heartstonecardsinfo.domain

import androidx.lifecycle.LiveData
import com.alexos.heartstonecardsinfo.data.network.ApiFactory
import com.alexos.heartstonecardsinfo.data.network.mapper.CardMapper
import io.reactivex.Single

class GetCardInfoUseCase{

    fun getCardInfo(cardInfoId: Int): CardInfo {
        return CardInfo(
            0,
            "a",
            0,
            0,
            0,
            "a",
            " "
        )
    }
}