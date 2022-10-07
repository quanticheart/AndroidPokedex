package com.quanticheart.core.dialog

import android.content.Context
import com.quanticheart.core.base.BaseFragmentDialog
import com.quanticheart.core.databinding.LayoutLoadingBinding

//
// Created by Jonn Alves on 19/09/22.
//
class LoadingDialog(context: Context) :
    BaseFragmentDialog<LayoutLoadingBinding>(context, LayoutLoadingBinding::inflate) {

    override fun view(binding: LayoutLoadingBinding) {
        isCancelable = false
    }
}
