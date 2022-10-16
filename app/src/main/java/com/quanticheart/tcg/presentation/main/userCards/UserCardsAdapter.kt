package com.quanticheart.tcg.presentation.main.userCards

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.quanticheart.core.extentions.setLinearLayout
import com.quanticheart.domain.model.pokemon.Card
import com.quanticheart.tcg.databinding.ItemPokemonCardCollectionBinding

class UserCardsAdapter(
    recyclerView: RecyclerView,
    private val clickListener: (Card) -> Unit
) : RecyclerView.Adapter<UserCardsAdapter.PokemonViewHolder>() {

    private var pokemons: List<Card> = arrayListOf()

    init {
        recyclerView.apply {
            adapter = this@UserCardsAdapter
            setLinearLayout()
        }
    }

    inner class PokemonViewHolder(val binding: ItemPokemonCardCollectionBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        return PokemonViewHolder(
            ItemPokemonCardCollectionBinding.inflate(
                LayoutInflater.from(parent.context), parent,
                false
            )
        )
    }

    override fun getItemCount() = pokemons.size

    fun addItems(pokemons: List<Card>) {
        this.pokemons = pokemons
        this.notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int): Unit =
        holder.binding.run {
            val pokemon = pokemons[position]
            name.text = pokemon.name
            root.setOnClickListener {
                clickListener(pokemon)
            }
        }
}