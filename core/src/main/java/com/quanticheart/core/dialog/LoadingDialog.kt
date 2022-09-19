package com.quanticheart.core.dialog

import android.content.Context
import com.quanticheart.core.base.BaseFragmentDialog
import com.quanticheart.core.databinding.DialogLoadingBinding

//
// Created by Jonn Alves on 19/09/22.
//
class LoadingDialog(context: Context) :
    BaseFragmentDialog<DialogLoadingBinding>(context, DialogLoadingBinding::inflate) {

    override fun view(binding: DialogLoadingBinding) {
        isCancelable = false
    }
}
