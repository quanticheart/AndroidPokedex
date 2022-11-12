package com.quanticheart.core.extentions

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle

inline fun <reified T> Activity.startActivity(bundle: Bundle? = null) {
    val intent = Intent(this, T::class.java)
    bundle?.let { extras ->
        intent.putExtras(extras)
    }
    startActivity(intent)
}

fun Activity.sharePokemon(cardName: String) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_SUBJECT, "Subject Here")
        putExtra(
            Intent.EXTRA_TEXT,
            "https://www.ligapokemon.com.br/?view=cards/card&card=$cardName%20(1%2F102)&ed=BS"
        )
    }
    startActivity(Intent.createChooser(intent, "Card $cardName"))
}

fun Activity.openLink(url: String) = startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
