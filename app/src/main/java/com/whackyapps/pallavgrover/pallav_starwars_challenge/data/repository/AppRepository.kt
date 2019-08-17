package com.whackyapps.pallavgrover.pallav_starwars_challenge.data.repository

import com.whackyapps.pallavgrover.pallav_starwars_challenge.data.model.FilmModel
import com.whackyapps.pallavgrover.pallav_starwars_challenge.data.model.PlanetModel
import com.whackyapps.pallavgrover.pallav_starwars_challenge.data.model.SpecieModel
import com.whackyapps.pallavgrover.pallav_starwars_challenge.data.model.PersonModel
import com.whackyapps.pallavgrover.pallav_starwars_challenge.data.model.SearchPersonResponseModel
import com.whackyapps.pallavgrover.pallav_starwars_challenge.data.network.ApiError
import io.reactivex.disposables.Disposable

interface AppRepository {

    fun getHomeResults(
        success: (SearchPersonResponseModel) -> Unit,
        failure: (ApiError) -> Unit = {},
        terminate: () -> Unit = {}
    ): Disposable

    fun getSearchResults(
        success: (SearchPersonResponseModel) -> Unit,
        failure: (ApiError) -> Unit = {},
        terminate: () -> Unit = {},query:String,page:Int
    ): Disposable

    fun getFilmById(
        success: (FilmModel) -> Unit,
        failure: (ApiError) -> Unit = {},
        terminate: () -> Unit = {}, url:String
    ): Disposable

    fun getPlanetById(
        success: (PlanetModel) -> Unit,
        failure: (ApiError) -> Unit = {},
        terminate: () -> Unit = {}, url:String
    ): Disposable

    fun getSpeciesById(
        success: (SpecieModel) -> Unit,
        failure: (ApiError) -> Unit = {},
        terminate: () -> Unit = {}, url:String
    ): Disposable

}