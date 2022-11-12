package com.quanticheart.tcg.presentation.login.recover

import com.quanticheart.core.base.fragment.BaseFragment
import com.quanticheart.core.dialog.msgDialog
import com.quanticheart.core.extentions.setBackToolbar
import com.quanticheart.tcg.databinding.FragmentRecoverBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class RecoverFragment :
    BaseFragment<RecoverViewModel, FragmentRecoverBinding>(FragmentRecoverBinding::inflate) {

    override val viewModel: RecoverViewModel by viewModel()

    override fun view(binding: FragmentRecoverBinding): Unit = binding.run {
        navigationBar.setBackToolbar()
        btRecover.setOnClickListener {
            viewModel.recover(email.text)
        }
    }

    override fun viewModel(viewModel: RecoverViewModel): Unit = viewModel.run {
        loading.observe(viewLifecycleOwner) {
            if (it) showLoading() else hideLoading()
        }

        error.observe(viewLifecycleOwner) {
            it?.let {
                activity?.msgDialog(it)
                viewModel.consumedError()
            }
        }

        success.observe(viewLifecycleOwner) {
            activity?.msgDialog(it) {
                onBackPressed()
            }
        }
    }
}



