package com.quanticheart.repository.retrofit.interceptor

import com.quanticheart.repository.BuildConfig
import okhttp3.logging.HttpLoggingInterceptor

class LoggingInterceptor {
    private val log = HttpLoggingInterceptor()
    init {
        when (BuildConfig.DEBUG) {
            true -> log.level = HttpLoggingInterceptor.Level.BODY
            false -> log.level = HttpLoggingInterceptor.Level.NONE
        }
    }
    fun create() = log
}