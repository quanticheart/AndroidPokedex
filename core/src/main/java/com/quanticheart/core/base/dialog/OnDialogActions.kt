package com.quanticheart.core.base.dialog

import androidx.viewbinding.ViewBinding

interface OnDialogActions<T : ViewBinding> {
    fun view(binding: T)
    fun onShow() {}
    fun onHide() {}
}
