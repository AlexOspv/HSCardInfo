package com.alexos.heartstonecardsinfo.domain

data class CardInfo(
    val dbfId: Int,
    val name: String,
    val cost: Int,
    val attack: Int,
    val health: Int,
    val text: String,
    val img: String?
)
