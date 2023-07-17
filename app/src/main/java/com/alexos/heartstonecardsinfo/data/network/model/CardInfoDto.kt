package com.alexos.heartstonecardsinfo.data.network.model

data class CardInfoDto(
    val dbfId: Int,
    val name: String?,
    val type: String?,
    val flavor: String?,
    val playerClass: String?,
    val img: String?
)
