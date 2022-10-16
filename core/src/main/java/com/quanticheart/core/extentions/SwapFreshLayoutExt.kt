package com.quanticheart.core.extentions

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.quanticheart.core.R

//
// Created by Jonn Alves on 16/10/22.
//
fun SwipeRefreshLayout.setRefreshListener(callback: () -> Unit) {
    this.apply {
        setColorSchemeResources(
            R.color.colorBgButton,
            R.color.colorBgButtonStroke
        )
        setOnRefreshListener {
            callback()
            this.isRefreshing = false
        }
    }
}