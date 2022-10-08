package com.quanticheart.tcg

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.quanticheart.core.components.ControlLayout
import com.quanticheart.domain.model.ViewState
import com.quanticheart.tcg.presentation.about.AboutActivity
import com.quanticheart.tcg.presentation.details.CardDetailsActivity
import com.quanticheart.tcg.presentation.main.MainActivity
import com.quanticheart.tcg.presentation.login.LoginActivity

//
// Created by Jonn Alves on 18/09/22.
//
fun Activity.goHome() =
    startActivity(Intent(this, MainActivity::class.java))

fun Activity.goLogin() =
    startActivity(Intent(this, LoginActivity::class.java))

fun Activity.goDetails() =
    startActivity(Intent(this, CardDetailsActivity::class.java))

fun Activity.goAbout() =
    startActivity(Intent(this, AboutActivity::class.java))

fun Activity.goTerms() =
    startActivity(Intent(this, AboutActivity::class.java))


fun <T> LiveData<ViewState<T>>.observeStateLayout(
    lifecycleOwner: LifecycleOwner,
    controlLayout: ControlLayout,
    callback: (data: T) -> Unit
) {
    observe(lifecycleOwner) {
        when (it) {
            is ViewState.Success -> {
                callback(it.data)
                controlLayout.showLayout()
            }
            is ViewState.Loading -> controlLayout.showLoading()
            is ViewState.Failure -> controlLayout.showError(it.throwable.message)
        }
    }
}