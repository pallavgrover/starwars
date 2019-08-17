package com.whackyapps.pallavgrover.pallav_starwars_challenge.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parceler
import kotlinx.android.parcel.Parcelize

@Entity
data class PersonModel(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id") var id: Int,
    @SerializedName("name") val name: String?,
    @SerializedName("height") val height: String?,
    @SerializedName("birth_year") val birth_year: String?,
    @SerializedName("species") val species: List<String>?,
    @SerializedName("homeworld") val homeworld: String?,
    @SerializedName("films") val films: List<String>?,
    @SerializedName("url") val url: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.createStringArrayList(),
        parcel.readString(),
        parcel.createStringArrayList(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(height)
        parcel.writeString(birth_year)
        parcel.writeStringList(species)
        parcel.writeString(homeworld)
        parcel.writeStringList(films)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PersonModel> {
        override fun createFromParcel(parcel: Parcel): PersonModel {
            return PersonModel(parcel)
        }

        override fun newArray(size: Int): Array<PersonModel?> {
            return arrayOfNulls(size)
        }
    }
}