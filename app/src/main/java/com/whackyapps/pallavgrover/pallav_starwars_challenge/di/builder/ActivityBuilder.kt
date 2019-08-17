package com.whackyapps.pallavgrover.pallav_starwars_challenge.di.builder

import com.whackyapps.pallavgrover.pallav_starwars_challenge.di.builder.MainActivityProviders
import com.whackyapps.pallavgrover.pallav_starwars_challenge.ui.MainActivity
import com.whackyapps.pallavgrover.pallav_starwars_challenge.ui.PersonDetailsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [MainActivityProviders::class])
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [DetailsActivityProviders::class])
    abstract fun bindPersonDetailsActivity(): PersonDetailsActivity

}