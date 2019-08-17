package com.whackyapps.pallavgrover.pallav_starwars_challenge.di.builder

import android.arch.lifecycle.ViewModel
import com.whackyapps.pallavgrover.pallav_starwars_challenge.di.qualifier.ViewModelKey
import com.whackyapps.pallavgrover.pallav_starwars_challenge.ui.HomeViewModel
import com.whackyapps.pallavgrover.pallav_starwars_challenge.ui.PersonDetailsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AppViewModelBuilder {
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PersonDetailsViewModel::class)
    abstract fun bindPersonalDetailsViewModel(homeViewModel: PersonDetailsViewModel): ViewModel

}