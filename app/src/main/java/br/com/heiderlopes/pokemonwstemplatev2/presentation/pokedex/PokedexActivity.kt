package br.com.heiderlopes.pokemonwstemplatev2.presentation.pokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.View
import android.widget.Toast
import br.com.heiderlopes.pokemonwstemplatev2.R
import br.com.heiderlopes.pokemonwstemplatev2.databinding.ActivityPokedexBinding
import br.com.heiderlopes.pokemonwstemplatev2.domain.model.ViewState
import com.squareup.picasso.Picasso
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*

class PokedexActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private val pokedexViewModel: PokedexViewModel by viewModel()
    private var pokemonNumber = ""
    private val picasso: Picasso by inject()
    private lateinit var tts: TextToSpeech

    private val viewBinding by lazy {
        ActivityPokedexBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tts = TextToSpeech(this, this)

        setContentView(viewBinding.root)

        registerObserver()

        pokemonNumber = intent.getStringExtra("POKEMON") ?: ""
    }

    private fun registerObserver() {
        pokedexViewModel.pokemonResult.observe(this) {

            when (it) {
                is ViewState.Success -> {
                    viewBinding.tvPokemonName.text = it.data.name

                    picasso.load("https://pokedexdx.herokuapp.com${it.data.imageURL}")
                        .placeholder(R.drawable.pokebola)
                        .into(viewBinding.ivPokemon)

                    viewBinding.tvPokemonDescription.text = it.data.description

                    viewBinding.loading.containerLoading.visibility = View.GONE
                    speech(it.data.description)
                }
                is ViewState.Loading -> {
                    viewBinding.loading.containerLoading.visibility = View.VISIBLE
                }
                is ViewState.Failure -> {
                    viewBinding.loading.containerLoading.visibility = View.GONE
                    showError(it.throwable)
                }
            }

        }

    }

    private fun showError(throwable: Throwable) {
        Toast.makeText(this, throwable.message, Toast.LENGTH_SHORT).show()
    }

    override fun onInit(p0: Int) {
        if (p0 != TextToSpeech.ERROR) {
            tts.language = Locale.US
        }
        pokedexViewModel.getPokemon(pokemonNumber)
    }

    private fun speech(text: String) {
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    override fun onDestroy() {
        super.onDestroy()
        tts.let {
            it.stop()
            it.shutdown()
        }
    }
}