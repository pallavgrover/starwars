package com.whackyapps.pallavgrover.pallav_starwars_challenge.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.whackyapps.pallavgrover.pallav_starwars_challenge.data.model.PersonModel

@Database(entities = [PersonModel::class], version = AppDatabase.VERSION)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        const val DB_NAME = "charachter.db"
        const val VERSION = 2
    }
    abstract fun foodDao(): PersonDao
}