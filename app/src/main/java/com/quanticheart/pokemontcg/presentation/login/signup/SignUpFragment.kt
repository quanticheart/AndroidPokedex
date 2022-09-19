package com.quanticheart.pokemontcg.presentation.login.signup

import com.quanticheart.core.base.BaseFragment
import com.quanticheart.pokemontcg.databinding.FragmentSignUpBinding
import com.quanticheart.pokemontcg.goHome
import com.quanticheart.pokemontcg.goTerms
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
