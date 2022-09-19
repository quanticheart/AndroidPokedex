@file:Suppress("unused")

package com.quanticheart.tcg

import android.app.Application
import com.quanticheart.domain.domainModules
import com.quanticheart.repository.dataModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppApplication)
            modules(presentationModules + domainModules + dataModules)
        }
    }
}