package com.quanticheart.core.extentions

import android.os.Handler

//
// Created by Jonn Alves on 18/09/22.
//
fun splashTime(callback: () -> Unit) =
    Handler().postDelayed({ callback() }, 3000)
