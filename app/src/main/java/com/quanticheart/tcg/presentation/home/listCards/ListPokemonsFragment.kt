package com.quanticheart.tcg.presentation.home.listCards

import android.os.Bundle
import com.quanticheart.core.base.BaseFragment
import com.quanticheart.core.extentions.startActivity
import com.quanticheart.domain.model.ViewState
import com.quanticheart.tcg.R
import com.quanticheart.tcg.databinding.FragmentListPokemonsBinding
import com.quanticheart.tcg.presentation.details.CardDetailsActivity
import com.quanticheart.tcg.presentation.details.constants.INTENT_KEY_DETAILS
import com.squareup.picasso.Picasso
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListPokemonsFragment :
    BaseFragment<ListPokemonsViewModel, FragmentListPokemonsBinding>(FragmentListPokemonsBinding::inflate) {

    private lateinit var adapter: ListPokemonsAdapter
    private val picasso: Picasso by inject()
    override val viewModel: ListPokemonsViewModel by viewModel()

    override fun view(binding: FragmentListPokemonsBinding): Unit = binding.run {
        adapter = ListPokemonsAdapter(list, picasso) {
            activity?.startActivity<CardDetailsActivity>(bundle = Bundle().apply {
                putSerializable(INTENT_KEY_DETAILS, it)
            })
        }

        contentError.btRetry.setOnClickListener {
            viewModel.getPokemons()
        }

        refresh.apply {
            setColorSchemeResources(
                R.color.colorBgButton,
                R.color.colorBgButtonStroke
            )
            setOnRefreshListener {
                viewModel.getPokemons()
                this.isRefreshing = false
            }
        }
    }

    override fun viewModel(viewModel: ListPokemonsViewModel): Unit = viewModel.run {
        pokemonResult.observe(viewLifecycleOwner) {
            when (it) {
                is ViewState.Success -> {
                    binding.flipper.displayedChild = 1
                    adapter.addItems(it.data)
                }
                is ViewState.Loading -> {
                    binding.flipper.displayedChild = 0
                }
                is ViewState.Failure -> {
                    binding.flipper.displayedChild = 2
                    binding.contentError.tvError.text = it.throwable.message
                }
            }
        }
        getPokemons()
    }
}