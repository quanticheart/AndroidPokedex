package com.quanticheart.core.extentions

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.setGridLayout(columns: Int = 3) {
    layoutManager = GridLayoutManager(this.context, columns)
}

fun RecyclerView.setLinearLayout() {
    layoutManager = LinearLayoutManager(this.context)
}