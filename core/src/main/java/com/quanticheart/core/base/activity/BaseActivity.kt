@file:Suppress("MemberVisibilityCanBePrivate")

package com.quanticheart.core.base.activity

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.quanticheart.core.construct.ViewConstruct
import com.quanticheart.core.dialog.LoadingDialog

typealias Inflate<T> = (LayoutInflater) -> T

abstract class BaseActivity<viewModel : ViewModel, VB : ViewBinding>(private val inflate: Inflate<VB>) :
    AppCompatActivity(),
    ViewConstruct<VB, viewModel> {

    private var _binding: VB? = null
    protected val binding get() = _binding!!

    private val loading: LoadingDialog? by lazy { LoadingDialog(this) }

    protected abstract val viewModel: viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = inflate.invoke(layoutInflater)
        setContentView(binding.root)
        view(binding)
        viewModel(viewModel)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun showLoading() = loading?.show()

    fun hideLoading() = loading?.dismiss()
}
