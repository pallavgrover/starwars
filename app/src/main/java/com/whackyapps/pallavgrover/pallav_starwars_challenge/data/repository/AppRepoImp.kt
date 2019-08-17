package com.whackyapps.pallavgrover.pallav_starwars_challenge.data.repository

import android.util.Log
import com.whackyapps.pallavgrover.pallav_starwars_challenge.data.model.FilmModel
import com.whackyapps.pallavgrover.pallav_starwars_challenge.data.model.PlanetModel
import com.whackyapps.pallavgrover.pallav_starwars_challenge.data.model.SpecieModel
import com.whackyapps.pallavgrover.pallav_starwars_challenge.data.db.AppDatabase
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import com.whackyapps.pallavgrover.pallav_starwars_challenge.data.model.PersonModel
import com.whackyapps.pallavgrover.pallav_starwars_challenge.data.model.SearchPersonResponseModel
import com.whackyapps.pallavgrover.pallav_starwars_challenge.data.network.ApiDisposable
import com.whackyapps.pallavgrover.pallav_starwars_challenge.data.network.ApiError
import com.whackyapps.pallavgrover.pallav_starwars_challenge.data.network.ApiService

class AppRepoImp(
    val apiService: ApiService
) : AppRepository {
    override fun getSpeciesById(
        success: (SpecieModel) -> Unit,
        failure: (ApiError) -> Unit,
        terminate: () -> Unit,
        url: String
    ): Disposable {
        return apiService
            .findSpecieById(url)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnTerminate(terminate)
            .subscribeWith(
                ApiDisposable<SpecieModel>(
                    {

                        success(it)
                    },
                    failure
                )
            )
    }

    override fun getPlanetById(
        success: (PlanetModel) -> Unit,
        failure: (ApiError) -> Unit,
        terminate: () -> Unit,
        url: String
    ): Disposable {
        return apiService
            .findPlanetById(url)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnTerminate(terminate)
            .subscribeWith(
                ApiDisposable<PlanetModel>(
                    {

                        success(it)
                    },
                    failure
                )
            )
    }

    override fun getFilmById(
        success: (FilmModel) -> Unit,
        failure: (ApiError) -> Unit,
        terminate: () -> Unit, url:String
    ): Disposable {
        return apiService
            .findFilmById(url)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnTerminate(terminate)
            .subscribeWith(
                ApiDisposable<FilmModel>(
                    {

                        success(it)
                    },
                    failure
                )
            )
    }



    override fun getSearchResults(
        success: (SearchPersonResponseModel) -> Unit,
        failure: (ApiError) -> Unit,
        terminate: () -> Unit,query:String,page:Int
    ): Disposable {
        return apiService
            .searchPeople(query,page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnTerminate(terminate)
            .subscribeWith(
                ApiDisposable<SearchPersonResponseModel>(
                    {

                        success(it)
                    },
                    failure
                )
            )
    }

    private val TAG = AppRepoImp::class.java.simpleName
    override fun getHomeResults(success: (SearchPersonResponseModel) -> Unit, failure: (ApiError) -> Unit, terminate: () -> Unit): Disposable {
        return apiService
            .getHome()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnTerminate(terminate)
            .subscribeWith(
                ApiDisposable<SearchPersonResponseModel>(
                    {

                        success(it)
                    },
                    failure
                )
            )
    }

}