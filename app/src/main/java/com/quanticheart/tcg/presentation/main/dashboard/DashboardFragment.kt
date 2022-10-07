package com.quanticheart.tcg.presentation.main.dashboard

import com.quanticheart.core.base.BaseFragment
import com.quanticheart.tcg.databinding.FragmentDashboardBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardFragment :
    BaseFragment<DashboardViewModel, FragmentDashboardBinding>(FragmentDashboardBinding::inflate) {

    override val viewModel: DashboardViewModel by viewModel()

    override fun view(binding: FragmentDashboardBinding): Unit = binding.run {
        btnAbout.setOnClickListener {

        }

        btnExit.setOnClickListener {

        }
    }

    override fun viewModel(viewModel: DashboardViewModel): Unit = viewModel.run {
    }
}
