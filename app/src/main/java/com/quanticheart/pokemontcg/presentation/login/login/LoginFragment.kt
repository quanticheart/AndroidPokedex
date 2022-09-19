package com.quanticheart.pokemontcg.presentation.login.login

import androidx.navigation.fragment.findNavController
import com.quanticheart.core.base.BaseFragment
import com.quanticheart.pokemontcg.R
import com.quanticheart.pokemontcg.databinding.FragmentLoginBinding
import com.quanticheart.pokemontcg.goHome
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment :
    BaseFragment<LoginViewModel, FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    override val viewModel: LoginViewModel by viewModel()

    override fun view(binding: FragmentLoginBinding): Unit = binding.run {
        btLogin.setOnClickListener {
            viewModel.doLogin(email.text, password.text)
        }

        tvResetPassword.setOnClickListener {

        }

        tvNewAccount.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }
    }

    override fun viewModel(viewModel: LoginViewModel): Unit = viewModel.run {
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



