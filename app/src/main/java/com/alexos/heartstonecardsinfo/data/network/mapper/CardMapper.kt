package com.alexos.heartstonecardsinfo.data.network.mapper

import com.alexos.heartstonecardsinfo.data.network.model.CardInfoDto
import com.alexos.heartstonecardsinfo.domain.CardInfo

object CardMapper {

    fun mapDtoToModel(dto: ArrayList<CardInfoDto>): ArrayList<CardInfo> {
        val cardInfoList = arrayListOf<CardInfo>()
        for (i in dto.indices) {
            cardInfoList.add(
                CardInfo(
                    dbfId = dto[i].dbfId,
                    name = dto[i].name,
                    cost = dto[i].cost,
                    attack = dto[i].attack,
                    health = dto[i].health,
                    text = dto[i].text,
                    img = dto[i].img
                )
            )
        }
        return cardInfoList
    }
}