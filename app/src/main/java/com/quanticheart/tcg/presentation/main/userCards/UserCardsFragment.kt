package com.quanticheart.tcg.presentation.main.userCards

import com.quanticheart.core.base.BaseFragment
import com.quanticheart.core.extentions.splashTime
import com.quanticheart.tcg.databinding.FragmentUserCardsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserCardsFragment : BaseFragment<UserCardsViewModel, FragmentUserCardsBinding>(
    FragmentUserCardsBinding::inflate
) {
    override val viewModel: UserCardsViewModel by viewModel()

    override fun view(binding: FragmentUserCardsBinding): Unit = binding.run {
        splashTime { container.showLayout() }
    }

    override fun viewModel(viewModel: UserCardsViewModel) {
    }
}
