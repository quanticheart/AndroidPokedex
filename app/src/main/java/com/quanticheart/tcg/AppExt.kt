package com.quanticheart.tcg

import android.app.Activity
import android.content.Intent
import com.quanticheart.tcg.presentation.about.AboutActivity
import com.quanticheart.tcg.presentation.details.CardDetailsActivity
import com.quanticheart.tcg.presentation.home.HomeActivity
import com.quanticheart.tcg.presentation.login.LoginActivity

//
// Created by Jonn Alves on 18/09/22.
//
fun Activity.goHome() =
    startActivity(Intent(this, HomeActivity::class.java))

fun Activity.goLogin() =
    startActivity(Intent(this, LoginActivity::class.java))

fun Activity.goDetails() =
    startActivity(Intent(this, CardDetailsActivity::class.java))

fun Activity.goAbout() =
    startActivity(Intent(this, AboutActivity::class.java))

fun Activity.goTerms() =
    startActivity(Intent(this, AboutActivity::class.java))

