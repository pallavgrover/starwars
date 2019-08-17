package com.whackyapps.pallavgrover.pallav_starwars_challenge.di.builder

import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module(includes = [
    (AppViewModelBuilder::class)
])
abstract class ViewModelBuilder {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}