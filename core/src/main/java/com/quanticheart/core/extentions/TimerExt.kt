package com.quanticheart.core.extentions

import android.content.Context
import android.os.Handler

//
// Created by Jonn Alves on 18/09/22.
//
fun Context.splashTime(callback: () -> Unit) {
    Handler().postDelayed({ //This method will be executed once the timer is over
        callback()
    }, 3000)
}