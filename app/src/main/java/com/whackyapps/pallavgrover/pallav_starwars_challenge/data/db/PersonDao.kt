package com.whackyapps.pallavgrover.pallav_starwars_challenge.data.db

import android.arch.persistence.room.*
import com.whackyapps.pallavgrover.pallav_starwars_challenge.data.model.PersonModel

@Dao
interface PersonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFood(food: PersonModel): Long

    @Delete
    fun deleteFood(food: PersonModel): Int

    @Query("SELECT * from PersonModel")
    fun selectAllFoods(): MutableList<PersonModel>

}