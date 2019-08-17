package com.whackyapps.pallavgrover.pallav_starwars_challenge.data.db

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.whackyapps.pallavgrover.pallav_starwars_challenge.data.model.FilmModel
import com.whackyapps.pallavgrover.pallav_starwars_challenge.data.model.SpecieModel

class Converters {

    @TypeConverter
    fun listToFilm(value: List<String>?): String {

        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToFilm(value: String): List<String>? {

        val objects = Gson().fromJson(value, Array<FilmModel>::class.java) as Array<String>
        val list = objects.toList()
        return list
    }
}