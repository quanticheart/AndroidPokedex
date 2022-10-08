package com.quanticheart.tcg.presentation.details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.quanticheart.core.extentions.*
import com.quanticheart.domain.model.ViewState
import com.quanticheart.domain.model.pokemon.Pokemon
import com.quanticheart.tcg.R
import com.quanticheart.tcg.databinding.ActivityCardDetailBinding
import com.quanticheart.tcg.observeStateLayout
import com.quanticheart.tcg.presentation.details.constants.INTENT_KEY_DETAILS
import com.squareup.picasso.Picasso
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class CardDetailsActivity : AppCompatActivity() {

    private val viewModel: CardDetailsViewModel by viewModel()
    private val picasso: Picasso by inject()
    private val binding by lazy { ActivityCardDetailBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) = binding.layout.run {
        super.onCreate(savedInstanceState)
        getSerializableExtra<Pokemon>(INTENT_KEY_DETAILS)?.let {
            setContentView(binding.root)
            observers()
            navigationBar.setBackToolbar()
            btnAddToColection.setOnClickListener {
                viewModel.addToCollection()
            }
            createTextToSpeech {
                viewModel.getPokemon(it)
            }
        } ?: run {
            toast(getString(R.string.msg_error_details))
            finish()
        }
    }

    private fun observers() = viewModel.run {
        pokemonResult.observe(this@CardDetailsActivity) {
            when (it) {
                is ViewState.Failure -> {
                    binding.flipper.showError(it.throwable.message)
                    toast(it.throwable)
                    finish()
                }
                ViewState.Loading -> binding.flipper.showLoading()
                is ViewState.Success -> {
                    binding.layout.run {
                        name.text = it.data.name
                        navigationBar.title = it.data.name
                        picasso.load(it.data.imageURL)
                            .placeholder(R.drawable.placeholder)
                            .into(image)

                        description.text = it.data.description

                        speak(it.data.description)
                        binding.flipper.showLayout()
                    }
                }
            }
        }

        addToCollection.observeStateLayout(this@CardDetailsActivity, binding.flipper) {

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        stopTextToSpeech()
    }
}