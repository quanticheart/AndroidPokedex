package com.quanticheart.tcg.presentation.home.userCards

import com.quanticheart.core.base.BaseFragment
import com.quanticheart.tcg.databinding.FragmentDashboardBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserCardsFragment :
    BaseFragment<UserCardsViewModel, FragmentDashboardBinding>(FragmentDashboardBinding::inflate) {

    override val viewModel: UserCardsViewModel by viewModel()

    override fun view(binding: FragmentDashboardBinding): Unit = binding.run {
    }

    override fun viewModel(viewModel: UserCardsViewModel) {
    }
}
