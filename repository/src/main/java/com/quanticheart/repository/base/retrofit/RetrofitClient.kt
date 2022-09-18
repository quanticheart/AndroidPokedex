package com.quanticheart.repository.base.retrofit

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.quanticheart.repository.base.retrofit.interceptor.AuthInterceptor
import com.quanticheart.repository.base.retrofit.interceptor.CacheInterceptor
import com.quanticheart.repository.base.retrofit.interceptor.LoggingInterceptor
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient(private val application: Context) {

    private val gson: Gson by lazy { GsonBuilder().create() }

    private val okHttp: OkHttpClient by lazy {
        val client = OkHttpClient.Builder()
            .cache(cacheSize())
            .addNetworkInterceptor(CacheInterceptor)
            .addInterceptor(AuthInterceptor())
            .addInterceptor(LoggingInterceptor().create())

        client.build()
    }

    fun newInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(HttpConstants.baseUrl)
            .client(okHttp)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    private fun cacheSize(): Cache {
        return Cache(application.cacheDir, HttpConstants.cacheSize)
    }
}