package com.quanticheart.tcg.presentation.main.userCards

import android.app.Activity
import com.quanticheart.core.base.fragment.BaseFragment
import com.quanticheart.core.extentions.createBroadcastManager
import com.quanticheart.core.extentions.destroyBroadcastManager
import com.quanticheart.core.extentions.sendBroadcastAction
import com.quanticheart.core.extentions.setRefreshListener
import com.quanticheart.tcg.databinding.FragmentUserCardsBinding
import com.quanticheart.tcg.goDetails
import com.quanticheart.tcg.observeStateLayout
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserCardsFragment : BaseFragment<UserCardsViewModel, FragmentUserCardsBinding>(
    FragmentUserCardsBinding::inflate
) {
    private val adapter: UserCardsAdapter by lazy {
        UserCardsAdapter(binding.layout.list) {
            activity?.goDetails(it.id)
        }
    }

    override val viewModel: UserCardsViewModel by viewModel()

    override fun view(binding: FragmentUserCardsBinding): Unit = binding.layout.run {
        refresh.setRefreshListener {
            viewModel.loadList()
        }
    }

    override fun viewModel(viewModel: UserCardsViewModel): Unit = viewModel.run {
        collection.observeStateLayout(viewLifecycleOwner, binding.container) {
            adapter.addItems(it)
            binding.layout.flipperList.displayedChild = if (it.isEmpty()) 0 else 1
        }
        loadList()
    }

    override fun onResume() {
        super.onResume()
        createBroadcastManager("reloadCollection") {
            viewModel.loadList()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        destroyBroadcastManager()
    }
}

fun Activity.reloadListCollection() {
    sendBroadcastAction("reloadCollection")
}