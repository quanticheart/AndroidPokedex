package com.quanticheart.core.construct

import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

//
// Created by Jonn Alves on 18/09/22.
//
interface ViewConstruct<VB : ViewBinding, viewModel : ViewModel> {
    fun view(binding: VB)
    fun viewModel(viewModel: viewModel)
}