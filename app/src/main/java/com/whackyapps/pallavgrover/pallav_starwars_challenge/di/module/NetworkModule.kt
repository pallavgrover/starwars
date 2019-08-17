package com.whackyapps.pallavgrover.pallav_starwars_challenge.di.module

import android.content.Context
import com.google.gson.Gson
import com.whackyapps.pallavgrover.pallav_starwars_challenge.core.Config
import com.whackyapps.pallavgrover.pallav_starwars_challenge.data.db.AppDatabase
import com.whackyapps.pallavgrover.pallav_starwars_challenge.data.network.ApiService
import com.whackyapps.pallavgrover.pallav_starwars_challenge.data.network.CachingControlInterceptor
import dagger.Module
import dagger.Provides
import com.whackyapps.pallavgrover.pallav_starwars_challenge.data.repository.AppRepoImp
import com.whackyapps.pallavgrover.pallav_starwars_challenge.data.repository.AppRepository
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
class NetworkModule {

    @Provides
    @Singleton
    fun providesRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        rxJava2CallAdapterFactory: RxJava2CallAdapterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder().baseUrl(Config.HOST)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(rxJava2CallAdapterFactory)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(applicationContext: Context): OkHttpClient {
        //setup cache
        val httpCacheDirectory = File(applicationContext.cacheDir, "responses")
        val cacheSize = 10 * 1024 * 1024 // 10 MiB
        val cache = Cache(httpCacheDirectory, cacheSize.toLong())
        val client = OkHttpClient.Builder()
            .cache(cache)
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        client.addNetworkInterceptor(CachingControlInterceptor(applicationContext))
        return client.build()
    }


    @Provides
    @Singleton
    fun providesGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun providesGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun providesRxJavaCallAdapterFactory(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }

    @Singleton
    @Provides
    fun provideService( retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun provideRepository(apiService: ApiService): AppRepository {
        return AppRepoImp(apiService)
    }


}