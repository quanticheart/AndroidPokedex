package com.quanticheart.repository.picasso

import android.content.Context
import com.quanticheart.repository.retrofit.interceptor.AuthInterceptor
import com.quanticheart.repository.retrofit.interceptor.CacheInterceptor
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import okhttp3.Cache
import okhttp3.OkHttpClient

private const val CACHE_SIZE = 5 * 1024 * 1024L // 5 MB de cache

class PicassoClient(
    private val application: Context
) {

    private val okHttp: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .cache(cacheSize())
            .addNetworkInterceptor(CacheInterceptor)
            .addInterceptor(AuthInterceptor())
            .build()
    }

    fun newInstance(): Picasso {
        return Picasso
            .Builder(application)
            .downloader(OkHttp3Downloader(okHttp))
            .build()
    }

    private fun cacheSize(): Cache {
        return Cache(application.cacheDir, CACHE_SIZE)
    }
}