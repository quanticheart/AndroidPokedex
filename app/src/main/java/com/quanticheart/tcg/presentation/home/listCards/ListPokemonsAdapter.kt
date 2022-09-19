package com.quanticheart.tcg.presentation.home.listCards

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.quanticheart.core.extentions.setGridLayout
import com.quanticheart.domain.model.pokemon.Pokemon
import com.quanticheart.tcg.R
import com.quanticheart.tcg.databinding.PokemonListItemBinding
import com.squareup.picasso.Picasso

class ListPokemonsAdapter(
    recyclerView: RecyclerView,
    private val picasso: Picasso,
    private val clickListener: (Pokemon) -> Unit
) : RecyclerView.Adapter<ListPokemonsAdapter.PokemonViewHolder>() {

    private var pokemons: List<Pokemon> = arrayListOf()

    init {
        recyclerView.apply {
            adapter = this@ListPokemonsAdapter
            setGridLayout()
        }
    }

    inner class PokemonViewHolder(val binding: PokemonListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        return PokemonViewHolder(
            PokemonListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent,
                false
            )
        )
    }

    override fun getItemCount() = pokemons.size

    fun addItems(pokemons: List<Pokemon>) {
        this.pokemons = pokemons
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = pokemons[position]
        picasso
            .load(pokemon.imageURL)
            .placeholder(R.drawable.placeholder)
            .into(holder.binding.ivPokemon)
        holder.binding.root.setOnClickListener {
            clickListener(pokemon)
        }
    }
}