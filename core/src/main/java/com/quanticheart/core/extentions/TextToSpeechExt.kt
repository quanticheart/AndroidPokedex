package com.quanticheart.core.extentions

import android.content.Context
import android.speech.tts.TextToSpeech
import java.util.*

var textToSpeech: TextToSpeech? = null

fun Context.createTextToSpeech(callback: () -> Unit) {
    textToSpeech = TextToSpeech(this) {
        if (it != TextToSpeech.ERROR) {
            textToSpeech?.language = Locale.US
        }
        callback()
    }
}

fun speak(msg: String) {
    textToSpeech?.speak(msg, TextToSpeech.QUEUE_FLUSH, null, null)
}

fun stopTextToSpeech() {
    textToSpeech?.let {
        it.stop()
        it.shutdown()
    }
}