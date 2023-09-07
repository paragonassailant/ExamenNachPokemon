package com.example.examennach.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.examennach.R
import com.example.examennach.data.entities.Pokemon
import com.example.examennach.databinding.ItemListPokemonBinding
import com.example.examennach.ui.main.auxiliar.IHelper
import com.example.examennach.ui.preferences.FavoritePreferences
import com.squareup.picasso.Picasso

class PokemonAdapter(
    var context: Context,
    private var lisPokemon: List<Pokemon>,
    var listener: IHelper
) :
    RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    private var favoritePreferences = FavoritePreferences(context)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_list_pokemon, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = lisPokemon[position]
        holder.binding.tvNP.text = item.name
        holder.binding.tvCandy.text = item.candy
        holder.binding.ibFavorite.isChecked = item.isChecked
        Picasso.with(context)
            .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + item.num + ".png")
            .fit()
            .centerCrop()
            .into(holder.binding.ivPokemon)

        holder.binding.cvA.setOnClickListener {
            listener.pokemon(item)
        }
        holder.binding.ibFavorite.setOnCheckedChangeListener { compoundButton, status ->
            item.isChecked = status
            listener.favorite(position, status)
        }
        if (favoritePreferences.isChecked()) {
            holder.binding.ibFavorite.isChecked = item.isChecked
        }

    }

    override fun getItemCount() = lisPokemon.size


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemListPokemonBinding.bind(view)
    }

}