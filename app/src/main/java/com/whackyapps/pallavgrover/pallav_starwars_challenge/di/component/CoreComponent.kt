package com.whackyapps.pallavgrover.pallav_starwars_challenge.di.component

import android.app.Application
import com.whackyapps.pallavgrover.pallav_starwars_challenge.di.module.DataBaseModule
import com.whackyapps.pallavgrover.pallav_starwars_challenge.di.module.NetworkModule
import com.whackyapps.pallavgrover.pallav_starwars_challenge.core.App
import com.whackyapps.pallavgrover.pallav_starwars_challenge.di.module.ContextModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import com.whackyapps.pallavgrover.pallav_starwars_challenge.di.builder.ActivityBuilder
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, NetworkModule::class,  ActivityBuilder::class,
     DataBaseModule::class, ContextModule::class])
interface CoreComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): CoreComponent
    }


}