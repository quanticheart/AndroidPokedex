package com.quanticheart.core.extentions

import android.content.res.Resources

//
// Created by Jonn Alves on 19/09/22.
//
val Int.toDp: Int
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()
