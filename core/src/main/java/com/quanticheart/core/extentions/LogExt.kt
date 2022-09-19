package com.quanticheart.core.extentions

import android.util.Log

fun Any?.logI(tag: String? = null) {
    Log.i(tag ?: "INFO", this?.toString() ?: "Empty var")
}

fun Any?.logE(tag: String? = null) {
    Log.e(tag ?: "WARNING", this?.toString() ?: "Empty var")
}
