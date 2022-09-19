@file:Suppress("MemberVisibilityCanBePrivate")

package com.quanticheart.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.quanticheart.core.construct.ViewConstruct

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment<viewModel : ViewModel, VB : ViewBinding>(private val inflate: Inflate<VB>) :
    Fragment(),
    ViewConstruct<VB, viewModel> {

    private var _binding: VB? = null
    protected val binding get() = _binding!!

    protected abstract val viewModel: viewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate.invoke(inflater, container, false)
        view(binding)
        viewModel(viewModel)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun showMessage(message: String?) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    fun finish() = activity?.finish()
}
