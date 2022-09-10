@file:Suppress("unused")

package com.quanticheart.pokemontcg

import android.app.Application
import com.quanticheart.domain.di.domainModules
import com.quanticheart.pokemontcg.di.presentationModules
import com.quanticheart.repository.di.dataModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@AppApplication)
            modules(presentationModules)
            modules(domainModules)
            modules(dataModules)
        }
    }
}