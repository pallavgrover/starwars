package com.whackyapps.pallavgrover.pallav_starwars_challenge.core

import android.content.Context
import android.support.multidex.MultiDex
import com.whackyapps.pallavgrover.pallav_starwars_challenge.di.component.DaggerCoreComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class App : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerCoreComponent
            .builder()
            .application(this)
            .build()
    }
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}