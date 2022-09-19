package com.quanticheart.tcg.presentation.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.quanticheart.core.extentions.splashTime
import com.quanticheart.tcg.goHome
import com.quanticheart.tcg.goLogin
import org.koin.androidx.viewmodel.ext.android.viewModel

//
// Created by Jonn Alves on 18/09/22.
//
class SplashActivity : AppCompatActivity() {

    private val viewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        splashScreen.setKeepOnScreenCondition { true }

        registerObserver()

        splashTime {
            viewModel.startSessionVerification()
        }
    }

    private fun registerObserver(): Unit = viewModel.run {
        offline.observe(this@SplashActivity) {
            goLogin()
            finish()
        }

        online.observe(this@SplashActivity) {
            goHome()
            finish()
        }
    }
}