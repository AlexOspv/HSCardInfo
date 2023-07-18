package com.alexos.heartstonecardsinfo.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CardInfo(
    val dbfId: Int,
    val name: String?,
    val type: String?,
    val flavor: String?,
    val playerClass: String?,
    val img: String?
):Parcelable {
    companion object {

        const val UNDEFINED_ID = -1
    }
}
