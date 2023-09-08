package com.example.examennach.ui.detail

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.examennach.data.entities.Pokemon
import com.example.examennach.databinding.FragmentPokemonDetailBinding
import com.example.examennach.sys.util.Constants.Companion.ID_POKEMON
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PokemonDetailFragment : Fragment() {

    private lateinit var binding: FragmentPokemonDetailBinding
    private lateinit var viewModel: PokemonViewModelLocal
    private var idPokemon: Int = 0
    private var tes: Int = 6
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding =
            FragmentPokemonDetailBinding.inflate(LayoutInflater.from(context), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = arguments
        idPokemon = args?.getInt(ID_POKEMON)!!.toInt()

        viewModel = ViewModelProvider(this)[PokemonViewModelLocal::class.java]
        viewModel.requestDetails(idPokemon)
        binding.ltAnim.visibility = View.VISIBLE

        setObservers()
    }

    private fun setObservers() {
        viewModel.onSuccess.observe(viewLifecycleOwner) {
            Handler(Looper.getMainLooper()).postDelayed({
                binding.ltAnim.visibility = View.GONE
                setUpDetails(it)
            },2000)

        }
    }

    private fun setUpDetails(pokemon: Pokemon) {
        binding.cvDetail.visibility = View.VISIBLE
        Picasso.with(requireContext())
            .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$idPokemon.png")
            .fit()
            .centerCrop()
            .into(binding.ivAvatar)

        binding.tvName.text = pokemon.name
        binding.tvH.text = pokemon.height
        binding.tvW.text = pokemon.weight
        binding.tvCandy.text = pokemon.candy
        binding.tvCC.text = pokemon.candy_count.toString()
        binding.tvE.text = pokemon.egg
        binding.tvsp.text = pokemon.spawn_chance.toString()
        binding.tvavg.text = pokemon.avg_spawns.toString()
        binding.tvTime.text = pokemon.spawn_time


    }
}