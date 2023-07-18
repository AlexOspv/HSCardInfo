package com.alexos.heartstonecardsinfo.data.network.mapper

import com.alexos.heartstonecardsinfo.data.network.model.CardInfoDto
import com.alexos.heartstonecardsinfo.domain.CardInfo

object CardMapper {

    fun mapDtoToModel(dto: List<CardInfoDto>): List<CardInfo> {
        val cardInfoList = arrayListOf<CardInfo>()
        for (i in dto.indices) {
            cardInfoList.add(
                CardInfo(
                    dbfId = dto[i].dbfId,
                    name = dto[i].name,
                    type = "Тип: " + dto[i].type,
                    flavor = if (dto[i].flavor.isNullOrEmpty()) "Nothing interesting :(" else dto[i].flavor,
                    playerClass = "Класс: " + dto[i].playerClass,
                    img = dto[i].img
                )
            )
        }
        return cardInfoList
    }
}