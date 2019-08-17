package com.whackyapps.pallavgrover.pallav_starwars_challenge

import com.whackyapps.pallavgrover.pallav_starwars_challenge.data.model.PersonModel
import com.whackyapps.pallavgrover.pallav_starwars_challenge.data.model.SearchPersonResponseModel
import com.whackyapps.pallavgrover.pallav_starwars_challenge.data.network.ApiService
import com.whackyapps.pallavgrover.pallav_starwars_challenge.data.repository.AppRepoImp
import com.whackyapps.pallavgrover.pallav_starwars_challenge.ui.HomeViewModel
import io.reactivex.Observable
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.plugins.RxAndroidPlugins
import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LifecycleRegistry
import org.junit.*
import org.mockito.Mockito.*
import org.mockito.Mockito.`when`
import org.mockito.Mockito.`when`


class RepositoryTest {

    @Mock
    private lateinit var service: ApiService
    @Rule @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var personRepository: AppRepoImp
    private lateinit var homeViewModel: HomeViewModel
    @Mock
    var lifecycleOwner: LifecycleOwner? = null
    var lifecycle: Lifecycle? = null


    @Before
    fun setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { schedulerCallable -> Schedulers.trampoline() }
        MockitoAnnotations.initMocks(this)
        lifecycle = lifecycleOwner?.let { LifecycleRegistry(it) };
        personRepository = AppRepoImp(service)
        homeViewModel = spy(HomeViewModel(personRepository))
    }

    @Test
    fun test_getStories_Success() {
        val person = listOf<PersonModel>()

        `when`(service.searchPeople("han",1)).thenReturn(Observable.just(SearchPersonResponseModel("pal","pal",1,person)))
        Thread.sleep(3000)

        homeViewModel.search("han",1)
        Thread.sleep(3000)
    }

    @Test
    fun testApiFetchDataError() {
        `when`(service.searchPeople("han",1)).thenReturn(Observable.error(Throwable("Api error")))
        Thread.sleep(2000)

        homeViewModel.search("han",1)
        Thread.sleep(2000)

    }

}