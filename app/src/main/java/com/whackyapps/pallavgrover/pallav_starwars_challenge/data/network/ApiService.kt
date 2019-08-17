package com.whackyapps.pallavgrover.pallav_starwars_challenge.data.network

import com.whackyapps.pallavgrover.pallav_starwars_challenge.data.model.FilmModel
import com.whackyapps.pallavgrover.pallav_starwars_challenge.data.model.PlanetModel
import com.whackyapps.pallavgrover.pallav_starwars_challenge.data.model.SpecieModel
import io.reactivex.Observable
import com.whackyapps.pallavgrover.pallav_starwars_challenge.data.model.SearchPersonResponseModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiService {

    @GET("people/")
    fun getHome(
    ): Observable<SearchPersonResponseModel>

    @GET("people/")
    fun searchPeople(@Query("search") query: String, @Query("page") page: Int)
            : Observable<SearchPersonResponseModel>

    @GET
    fun findFilmById(@Url id: String)
            : Observable<FilmModel>

    @GET
    fun findSpecieById(@Url id: String)
            : Observable<SpecieModel>

    @GET
    fun findPlanetById(@Url id: String)
            : Observable<PlanetModel>
}