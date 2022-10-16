package com.quanticheart.tcg.presentation.main.listCards

import com.quanticheart.core.base.fragment.BaseFragment
import com.quanticheart.core.extentions.setRefreshListener
import com.quanticheart.domain.model.ViewState
import com.quanticheart.domain.model.pokemon.Pokemon
import com.quanticheart.tcg.databinding.FragmentListPokemonsBinding
import com.quanticheart.tcg.goDetails
import com.quanticheart.tcg.goDetailsDialog
import com.squareup.picasso.Picasso
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListPokemonsFragment :
    BaseFragment<ListPokemonsViewModel, FragmentListPokemonsBinding>(FragmentListPokemonsBinding::inflate) {

    private lateinit var adapter: ListPokemonsAdapter
    private val picasso: Picasso by inject()
    override val viewModel: ListPokemonsViewModel by viewModel()

    override fun view(binding: FragmentListPokemonsBinding): Unit = binding.layout.run {
        adapter = ListPokemonsAdapter(list, picasso, object : ListPokemonsAdapterClickListener {
            override fun click(pokemon: Pokemon) {
                activity?.goDetails(pokemon.id)
            }

            override fun longClick(pokemon: Pokemon) {
                activity?.goDetailsDialog(picasso, pokemon)
            }
        })

        binding.container.setTryReloadCallback {
            viewModel.getPokemons()
        }

        refresh.setRefreshListener {
            viewModel.getPokemons()
        }
    }

    override fun viewModel(viewModel: ListPokemonsViewModel): Unit = viewModel.run {
        pokemonResult.observe(viewLifecycleOwner) {
            when (it) {
                is ViewState.Success -> {
                    adapter.addItems(it.data)
                    binding.container.showLayout()
                }
                is ViewState.Loading -> binding.container.showLoading()
                is ViewState.Failure -> binding.container.showError(it.throwable.message)
            }
        }
        getPokemons()
    }
}