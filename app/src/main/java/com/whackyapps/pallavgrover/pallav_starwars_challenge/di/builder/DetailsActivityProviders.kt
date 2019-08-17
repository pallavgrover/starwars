package com.whackyapps.pallavgrover.pallav_starwars_challenge.di.builder

import com.whackyapps.pallavgrover.pallav_starwars_challenge.ui.PersonDetailsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class DetailsActivityProviders{
    @ContributesAndroidInjector
    abstract fun provideHomeFragment(): PersonDetailsFragment

}