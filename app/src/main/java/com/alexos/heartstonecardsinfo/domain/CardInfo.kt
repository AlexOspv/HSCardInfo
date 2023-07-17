package com.alexos.heartstonecardsinfo.domain

data class CardInfo(
    val dbfId: Int,
    val name: String?,
    val type: String?,
    val flavor: String?,
    val playerClass: String?,
    val img: String?
) {
    companion object {

        const val UNDEFINED_ID = -1
    }
}
