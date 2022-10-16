package com.quanticheart.tcg

import android.app.Activity
import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.quanticheart.core.components.ControlLayout
import com.quanticheart.core.extentions.startActivity
import com.quanticheart.domain.model.ViewState
import com.quanticheart.domain.model.pokemon.Pokemon
import com.quanticheart.tcg.presentation.about.AboutActivity
import com.quanticheart.tcg.presentation.details.CardDetailsActivity
import com.quanticheart.tcg.presentation.login.LoginActivity
import com.quanticheart.tcg.presentation.main.MainActivity
import com.quanticheart.tcg.presentation.main.dialogDetails.DialogCardDetails
import com.squareup.picasso.Picasso

const val INTENT_KEY_DETAILS = "intent_key_details"

//
// Created by Jonn Alves on 18/09/22.
//
fun Activity.goHome() = startActivity<MainActivity>()

fun Activity.goLogin() = startActivity<LoginActivity>()

fun Activity.goDetails(id: String) =
    startActivity<CardDetailsActivity>(bundle = Bundle().apply {
        putSerializable(INTENT_KEY_DETAILS, id)
    })

fun Activity.goDetailsDialog(picasso: Picasso, pokemon: Pokemon) =
    DialogCardDetails(this, picasso, pokemon).show()

fun Activity.goAbout() = startActivity<AboutActivity>()

fun Activity.goTerms() = startActivity<AboutActivity>()

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