package com.whackyapps.pallavgrover.pallav_starwars_challenge.ui

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.whackyapps.pallavgrover.pallav_starwars_challenge.data.network.ApiError
import com.whackyapps.pallavgrover.pallav_starwars_challenge.ui.base.BaseViewModel
import com.whackyapps.pallavgrover.pallav_starwars_challenge.data.model.SearchPersonResponseModel
import com.whackyapps.pallavgrover.pallav_starwars_challenge.data.repository.AppRepository
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val appRepository: AppRepository) : BaseViewModel() {
    private val TAG = HomeViewModel::class.java.simpleName
    val homeData: MutableLiveData<SearchPersonResponseModel> by lazy { MutableLiveData<SearchPersonResponseModel>() }
    val searchResults: MutableLiveData<SearchPersonResponseModel> = MutableLiveData()
    val resetResults: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }


    val error : MutableLiveData<ApiError> by lazy { MutableLiveData<ApiError>() }
    val loadingSearchResultsLiveData
            : MutableLiveData<Boolean> = MutableLiveData()
    var next: String? = null
    var currentQuery: String = ""
    var currentPage = 1
    var searchPage : Int = 0

    fun search(newQuery:String , page:Int){
        if (newQuery.isNotEmpty()) {

            if (newQuery.equals(currentQuery) && page==searchPage) {
                return
            }
            searchPage = page
            loadingSearchResultsLiveData.value = true
            appRepository.getSearchResults( { searchData ->
                Log.d(TAG, "getHomeData.success() called with: $searchData")
                next = searchData.next
                loadingSearchResultsLiveData.value = false
                searchResults.postValue(searchData)
            },
                {
                    Log.d(TAG, "getHomeData.error() called with: $it")
                    error.value = it
                    loadingSearchResultsLiveData.value = false
                },
                {

                },newQuery,page
            ).also { compositeDisposable.add(it) }
        } else {
            resetList()
        }
        currentQuery = newQuery

    }

    private fun resetList() {
        Log.d("paginator","cu :"+currentPage)
        currentPage = 1
        resetResults.value = false
    }

    fun loadData(c: Int) {
        if (!isLoadingMoreFor(c + 1) && !isLastPage()) {
            search(currentQuery, c + 1)
            currentPage = c + 1
        }
    }

    fun isLastPage(): Boolean {
        return next == null
    }
    private fun isLoadingMoreFor(c: Int): Boolean {
        val isLoading = c == this.currentPage && loadingSearchResultsLiveData.value == true
        return isLoading
    }

}