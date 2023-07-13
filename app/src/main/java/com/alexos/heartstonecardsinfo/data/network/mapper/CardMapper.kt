package com.alexos.heartstonecardsinfo.data.network.mapper

import com.alexos.heartstonecardsinfo.data.network.model.CardInfoDto
import com.alexos.heartstonecardsinfo.domain.CardInfo

class CardMapper {

    fun mapDtoToModel(dto: CardInfoDto) = CardInfo(
        dbfId = dto.dbfId,
        name = dto.name,
        cost = dto.cost,
        attack = dto.attack,
        health = dto.health,
        text = dto.text,
        img = dto.img
    )
}