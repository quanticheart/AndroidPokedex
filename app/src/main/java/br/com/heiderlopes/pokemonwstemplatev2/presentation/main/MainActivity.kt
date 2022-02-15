package br.com.heiderlopes.pokemonwstemplatev2.presentation.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.heiderlopes.pokemonwstemplatev2.databinding.ActivityMainBinding
import br.com.heiderlopes.pokemonwstemplatev2.presentation.listpokemons.ListPokemonsActivity
import br.com.heiderlopes.pokemonwstemplatev2.presentation.scan.ScanActivity

class MainActivity : AppCompatActivity() {
    private val viewBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
        setUpListeners()
    }

    private fun setUpListeners() { viewBinding.btPokemonList.setOnClickListener {
        startActivity(Intent(this, ListPokemonsActivity::class.java)) }
        viewBinding.btPokedex.setOnClickListener {
            startActivity(Intent(this, ScanActivity::class.java))
        }
    }
}