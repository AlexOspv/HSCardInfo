package com.alexos.heartstonecardsinfo.data.network

import com.alexos.heartstonecardsinfo.data.network.model.CardInfoDto
import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("cards/sets/Classic")
    fun getCardsInfoList(
        @Query("locale") locale: String = "ruRU"
    ): Single<ArrayList<CardInfoDto>>
}