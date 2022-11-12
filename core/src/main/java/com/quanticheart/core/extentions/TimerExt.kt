package com.quanticheart.core.extentions

import android.os.Handler
import android.os.Looper

//
// Created by Jonn Alves on 18/09/22.
//
fun splashTime(callback: () -> Unit) =
    Handler(Looper.myLooper()!!).postDelayed({ callback() }, 2000)
