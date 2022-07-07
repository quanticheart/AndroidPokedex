package com.quanticheart.core.extentions

import android.app.Activity
import android.content.Intent
import android.os.Bundle

inline fun <reified T> Activity.startActivity(bundle: Bundle? = null) {
    val intent = Intent(this, T::class.java)
    bundle?.let { extras ->
        intent.putExtras(extras)
    }
    startActivity(intent)
}

@Suppress("UNCHECKED_CAST")
fun <T> Activity.getSerializableExtra(key: String): T? {
    return try {
        intent.getSerializableExtra(key)?.let {
            it as T
        } ?: run {
            null
        }
    } catch (e: Exception) {
        null
    }
}