package com.quanticheart.pokemontcg.presentation.details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.quanticheart.core.extentions.*
import com.quanticheart.domain.model.ViewState
import com.quanticheart.domain.model.pokemon.Pokemon
import com.quanticheart.pokemontcg.R
import com.quanticheart.pokemontcg.databinding.ActivityPokedexBinding
import com.quanticheart.pokemontcg.presentation.constants.INTENT_KEY_DETAILS
import com.squareup.picasso.Picasso
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class CardDetailsActivity : AppCompatActivity() {

    private val viewModel: CardDetailsViewModel by viewModel()
    private val picasso: Picasso by inject()
    private val binding by lazy { ActivityPokedexBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSerializableExtra<Pokemon>(INTENT_KEY_DETAILS)?.let {
            setContentView(binding.root)
            observers()
            binding.navigationBar.setBackToolbar()
            createTextToSpeech {
                viewModel.getPokemon(it)
            }
        } ?: run {
            toast(getString(R.string.msg_error_details))
            finish()
        }
    }

    private fun observers() {
        viewModel.pokemonResult.observe(this) {
            when (it) {
                is ViewState.Failure -> {
                    binding.flipper.displayedChild = 0
                    toast(it.throwable)
                    finish()
                }
                ViewState.Loading -> binding.flipper.displayedChild = 0
                is ViewState.Success -> {
                    binding.run {
                        name.text = it.data.name
                        binding.navigationBar.title = it.data.name
                        picasso.load(it.data.imageURL)
                            .placeholder(R.drawable.placeholder)
                            .into(binding.image)

                        description.text = it.data.description

                        speak(it.data.description)
                        binding.flipper.displayedChild = 1
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        stopTextToSpeech()
    }
}