package com.whackyapps.pallavgrover.pallav_starwars_challenge.ui

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.whackyapps.pallavgrover.pallav_starwars_challenge.data.model.*
import com.whackyapps.pallavgrover.pallav_starwars_challenge.data.network.ApiError
import com.whackyapps.pallavgrover.pallav_starwars_challenge.ui.base.BaseViewModel
import com.whackyapps.pallavgrover.pallav_starwars_challenge.data.repository.AppRepository
import javax.inject.Inject

class PersonDetailsViewModel @Inject constructor(private val appRepository: AppRepository) : BaseViewModel() {
    private val TAG = PersonDetailsViewModel::class.java.simpleName
    val filmLiveData: MutableLiveData<FilmModel> = MutableLiveData()
    val speciesLiveData: MutableLiveData<SpecieModel> by lazy { MutableLiveData<SpecieModel>() }
    val planetLiveData: MutableLiveData<PlanetModel> by lazy { MutableLiveData<PlanetModel>() }
    val personLiveData: MutableLiveData<PersonModel> by lazy { MutableLiveData<PersonModel>() }

    val error : MutableLiveData<ApiError> by lazy { MutableLiveData<ApiError>() }


    fun fetchFilms(url:List<String>){
        for(ur in url) {
            appRepository.getFilmById(

                { searchData ->
                    Log.d(TAG, "getHomeData.success() called with: $searchData")
                    filmLiveData.setValue(searchData)
                },
                {
                    Log.d(TAG, "getHomeData.error() called with: $it")
                    error.value = it
                },
                {

                }, ur
            ).also { compositeDisposable.add(it) }
        }
    }
    fun fetchSpecies(url:List<String>){
        for(ur in url) {
            appRepository.getSpeciesById(

                { searchData ->
                    Log.d(TAG, "getHomeData.success() called with: $searchData")
                    speciesLiveData.postValue(searchData)
                },
                {
                    Log.d(TAG, "getHomeData.error() called with: $it")
                    error.value = it
                },
                {

                }, ur
            ).also { compositeDisposable.add(it) }
        }
    }
    fun fetchPlanets(url:String){
            appRepository.getPlanetById(

                { searchData ->
                    Log.d(TAG, "getHomeData.success() called with: $searchData")
                    planetLiveData.postValue(searchData)
                },
                {
                    Log.d(TAG, "getHomeData.error() called with: $it")
                    error.value = it
                },
                {

                }, url
            ).also { compositeDisposable.add(it) }
    }

    fun onPersonRecieved(model:PersonModel){
        personLiveData.value = model
        model.films?.let{
            fetchFilms(it)
        }
        model.homeworld?.let{
            fetchPlanets(it)
        }
        model.species?.let{
            fetchSpecies(it)
        }
    }


}