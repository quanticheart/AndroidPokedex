package com.quanticheart.pokemontcg.presentation.listCards

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.quanticheart.core.extentions.startActivity
import com.quanticheart.domain.model.ViewState
import com.quanticheart.pokemontcg.R
import com.quanticheart.pokemontcg.constants.INTENT_KEY_DETAILS
import com.quanticheart.pokemontcg.databinding.ActivityListPokemonsBinding
import com.quanticheart.pokemontcg.presentation.details.CardDetailsActivity
import com.squareup.picasso.Picasso
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListPokemonsActivity : AppCompatActivity() {

    private lateinit var adapter: ListPokemonsAdapter
    private val viewModel: ListPokemonsViewModel by viewModel()
    private val picasso: Picasso by inject()
    private val binding by lazy {
        ActivityListPokemonsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        adapter = ListPokemonsAdapter(binding.list, picasso) {
            startActivity<CardDetailsActivity>(bundle = Bundle().apply {
                putSerializable(INTENT_KEY_DETAILS, it)
            })
        }

        binding.contentError.btRetry.setOnClickListener {
            viewModel.getPokemons()
        }

        binding.refresh.apply {
            setColorSchemeResources(
                R.color.colorBgButton,
                R.color.colorBgButtonStroke
            )
            setOnRefreshListener {
                viewModel.getPokemons()
                this.isRefreshing = false
            }
        }
        registerObserver()
    }

    private fun registerObserver() {
        viewModel.pokemonResult.observe(this) {
            when (it) {
                is ViewState.Success -> {
                    binding.flipper.displayedChild = 1
                    adapter.addItems(it.data)
                }
                is ViewState.Loading -> {
                    binding.flipper.displayedChild = 0
                }
                is ViewState.Failure -> {
                    binding.flipper.displayedChild = 3
                    binding.contentError.tvError.text = it.throwable.message
                }
            }
        }
        viewModel.getPokemons()
    }
}