package com.quanticheart.tcg.presentation.details

import com.quanticheart.core.base.activity.BaseActivity
import com.quanticheart.core.dialog.msgDialog
import com.quanticheart.core.extentions.*
import com.quanticheart.domain.model.ViewState
import com.quanticheart.domain.model.pokemon.Pokemon
import com.quanticheart.tcg.INTENT_KEY_DETAILS
import com.quanticheart.tcg.R
import com.quanticheart.tcg.databinding.ActivityCardDetailBinding
import com.squareup.picasso.Picasso
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class CardDetailsActivity :
    BaseActivity<CardDetailsViewModel, ActivityCardDetailBinding>(ActivityCardDetailBinding::inflate) {

    override val viewModel: CardDetailsViewModel by viewModel()
    private val picasso: Picasso by inject()

    override fun view(binding: ActivityCardDetailBinding) = binding.layout.run {
        getSerializableExtra<Pokemon>(INTENT_KEY_DETAILS)?.let {
            navigationBar.setBackToolbar()
            btnAddToColection.setOnClickListener {
                viewModel.collectionUpdate()
            }
            createTextToSpeech {
                viewModel.getPokemon(it)
            }
        } ?: run {
            toast(getString(R.string.msg_error_details))
            finish()
        }
    }

    override fun viewModel(viewModel: CardDetailsViewModel) = viewModel.run {
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

        collectionUpdate.observe(this@CardDetailsActivity) {
            when (it) {
                is ViewState.Failure -> {
                    toast(it.throwable)
                    binding.flipper.showLayout()
                }
                is ViewState.Loading -> binding.flipper.showLoading()
                is ViewState.Success -> {
                    binding.flipper.showLayout()
                    toast(getString(R.string.msg_collection_updated))
                }
            }
        }

        statusCollection.observe(this@CardDetailsActivity) {
            binding.layout.btnAddToColection.text =
                if (it == true) getString(R.string.label_remove)
                else getString(R.string.label_add)
        }

        errorVerifyCollection.observe(this@CardDetailsActivity) {
            msgDialog(it.message ?: "Error") {
                finish()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        stopTextToSpeech()
    }
}