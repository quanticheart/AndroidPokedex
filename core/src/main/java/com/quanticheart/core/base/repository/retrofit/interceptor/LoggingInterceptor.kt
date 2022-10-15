package com.quanticheart.core.base.repository.retrofit.interceptor

import com.quanticheart.core.BuildConfig
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