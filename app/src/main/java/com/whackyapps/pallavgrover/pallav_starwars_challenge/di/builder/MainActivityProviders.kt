package com.whackyapps.pallavgrover.pallav_starwars_challenge.di.builder

import com.whackyapps.pallavgrover.pallav_starwars_challenge.ui.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityProviders{
    @ContributesAndroidInjector
    abstract fun provideHomeFragment(): HomeFragment

}