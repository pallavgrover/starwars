package com.whackyapps.pallavgrover.pallav_starwars_challenge.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class SearchPersonResponseModel(
    @SerializedName("next") val next: String?,
    @SerializedName("previous") val previous: String?,
    @SerializedName("count") val count: Int?,
    @SerializedName("results") val results: List<PersonModel>)  {
    companion object {
        val STATUS_OK = "OK"
    }
}

