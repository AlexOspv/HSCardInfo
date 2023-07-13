package com.alexos.heartstonecardsinfo.data.network.model

data class CardInfoDto(
    val dbfId: Int,
    val name: String?,
    val cost: Int?,
    val attack: Int?,
    val health: Int?,
    val text: String?,
    val img: String?
)
