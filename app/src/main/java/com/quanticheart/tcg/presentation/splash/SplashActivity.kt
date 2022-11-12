package com.quanticheart.tcg.presentation.splash

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
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

        FirebaseMessaging.getInstance().token
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    return@OnCompleteListener
                }
                val token = task.result
                Log.e("newToken", token)
                getPreferences(Context.MODE_PRIVATE).edit().putString("fb", token)
                    .apply()
            })

        Log.d(
            "newToken",
            getPreferences(Context.MODE_PRIVATE).getString("fb", "empty :(") ?: "NULL"
        )
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