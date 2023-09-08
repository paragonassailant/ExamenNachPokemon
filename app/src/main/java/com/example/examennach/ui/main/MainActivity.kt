package com.example.examennach.ui.main


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.examennach.R
import com.example.examennach.data.entities.Pokemon
import com.example.examennach.databinding.ActivityMainBinding
import com.example.examennach.sys.util.Constants.Companion.ID_POKEMON
import com.example.examennach.ui.detail.PokemonDetailFragment
import com.example.examennach.ui.main.adapter.PokemonAdapter
import com.example.examennach.ui.main.auxiliar.IHelper
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), IHelper {

    private lateinit var viewModel: PokemonViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var pokemonAdapter: PokemonAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        viewModel = ViewModelProvider(this)[PokemonViewModel::class.java]
        viewModel.requestPokemon()
        binding.ltAnim.visibility = View.VISIBLE

        setObservers()
    }

    private fun setObservers() {
        viewModel.onSuccess.observe(this) {
            Handler(Looper.getMainLooper()).postDelayed({
                setUpRecyclerView(it)
                binding.ltAnim.visibility = View.GONE
            }, 2000)

        }
        viewModel.onError.observe(this) {
            Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun setUpRecyclerView(pokemon: List<Pokemon>) {
        binding.llR.visibility = View.VISIBLE
        binding.rvPokemon.layoutManager = LinearLayoutManager(this)
        pokemonAdapter = PokemonAdapter(this, pokemon, this)
        binding.rvPokemon.apply {
            binding.rvPokemon.setHasFixedSize(true)
            binding.rvPokemon.adapter = pokemonAdapter
        }
    }

    override fun pokemon(pokemon: Pokemon) {
        openDetailFragment(pokemon.id)
    }

    override fun favorite(position: Int, isChecked: Boolean) {
        if (isChecked) {

        } else {

        }

    }


    private fun openDetailFragment(id: Int) {
        val bundle = Bundle()
        val fragment = PokemonDetailFragment()
        bundle.putInt(ID_POKEMON, id)
        fragment.arguments = bundle
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        ft.add(R.id.main_container, fragment)
        ft.addToBackStack(null)
        ft.commit()
    }
}