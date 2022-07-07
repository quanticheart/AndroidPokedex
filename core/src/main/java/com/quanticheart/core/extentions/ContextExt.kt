package com.quanticheart.core.extentions

import android.content.Context
import android.widget.Toast

fun Context.toast(msg: String) =
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

fun Context.toast(msg: Throwable) =
    Toast.makeText(this, msg.message ?: "error", Toast.LENGTH_SHORT).show()
