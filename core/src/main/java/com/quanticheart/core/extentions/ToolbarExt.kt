package com.quanticheart.core.extentions

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.Toolbar
import com.quanticheart.core.R

fun Toolbar.setBackToolbar(title: String? = null) {
    val context = this.context as AppCompatActivity
    context.setSupportActionBar(this)
    context.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    context.supportActionBar?.setDisplayShowHomeEnabled(true)
    navigationIcon = AppCompatResources.getDrawable(context, R.drawable.ic_back_24)
    setNavigationOnClickListener {
        context.onBackPressedDispatcher.onBackPressed()
    }
    title?.let {
        this.title = it
    }
}