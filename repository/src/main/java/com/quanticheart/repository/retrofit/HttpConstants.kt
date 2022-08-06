package com.quanticheart.repository.retrofit

import com.quanticheart.repository.BuildConfig

//
// Created by Jonn Alves on 05/08/22.
//
object HttpConstants {
    const val baseUrl: String = BuildConfig.baseUrl
    const val authKey: String = BuildConfig.authKey
    const val cacheSize = 5 * 1024 * 1024L // 5 MB de cache
}