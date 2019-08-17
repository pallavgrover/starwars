package com.whackyapps.pallavgrover.pallav_starwars_challenge.di.module

import android.app.Application
import android.arch.persistence.room.Room
import com.whackyapps.pallavgrover.pallav_starwars_challenge.data.db.AppDatabase
import com.whackyapps.pallavgrover.pallav_starwars_challenge.data.db.PersonDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(application: Application): AppDatabase {
        return Room
            .databaseBuilder(application, AppDatabase::class.java, AppDatabase.DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideUserDao(appDataBase: AppDatabase): PersonDao {
        return appDataBase.foodDao()
    }
}