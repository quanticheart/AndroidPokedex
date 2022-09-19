package com.quanticheart.tcg.presentation.login.signup

import com.quanticheart.core.base.BaseFragment
import com.quanticheart.tcg.databinding.FragmentSignUpBinding
import com.quanticheart.tcg.goHome
import com.quanticheart.tcg.goTerms
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpFragment :
    BaseFragment<SignUpViewModel, FragmentSignUpBinding>(FragmentSignUpBinding::inflate) {

    private var checkBoxDone = false
    override val viewModel: SignUpViewModel by viewModel()

    override fun view(binding: FragmentSignUpBinding): Unit = binding.run {
        tvTerms.setOnClickListener {
            activity?.goTerms()
        }

        btCreateAccount.setOnClickListener {
            viewModel.create(
                name.text,
                email.text,
                password.text
            )
        }

        cbTermsSignUp.setOnClickListener {
            if (checkBoxDone) {
                cbTermsSignUp.speed = -1f
                cbTermsSignUp.playAnimation()
                checkBoxDone = false
            } else {
                cbTermsSignUp.speed = 1f
                cbTermsSignUp.playAnimation()
                checkBoxDone = true
            }
        }
    }

    override fun viewModel(viewModel: SignUpViewModel): Unit = viewModel.run {
        loading.observe(viewLifecycleOwner) {

        }

        error.observe(viewLifecycleOwner) {

        }

        success.observe(viewLifecycleOwner) {
            activity?.goHome()
            finish()
        }
    }
}
