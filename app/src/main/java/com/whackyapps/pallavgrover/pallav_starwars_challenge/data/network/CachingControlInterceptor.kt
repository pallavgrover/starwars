package com.whackyapps.pallavgrover.pallav_starwars_challenge.data.network

import android.content.Context
import com.whackyapps.pallavgrover.pallav_starwars_challenge.core.App
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

import java.io.IOException
import android.net.ConnectivityManager



class CachingControlInterceptor(applicationContext: Context) : Interceptor {

    var appContext:Context = applicationContext
//    fun CachingControlInterceptor(applicationContext: Context){
//        appContext = applicationContext
//    }
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        // Add Cache Control only for GET methods
        if (request.method() == "GET") {
            if (isNetworkAvailable(appContext)) {
                // 1 day
                request = request.newBuilder()
                    .header("Cache-Control", "only-if-cached")
                    .build()
            } else {
                // 4 weeks stale
                request = request.newBuilder()
                    .header("Cache-Control", "public, max-stale=2419200")
                    .build()
            }
        }

        val originalResponse = chain.proceed(request)
        return originalResponse.newBuilder()
            .header("Cache-Control", "max-age=600")
            .build()
    }

    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo.isConnected
    }
}