package com.miguel.disney.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.miguel.disney.databinding.FragmentFavoriteBinding

class FavoriteFragment : Fragment() {

    private lateinit var viewModel: PersonajeFavoriteViewModel
    private lateinit var binding: FragmentFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[PersonajeFavoriteViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = RVPersonajeListAdapter(listOf()) { personaje ->
            val destination = FavoriteFragmentDirections.actionFavoriteFragmentToPersonajeDetailFragment(personaje)
            findNavController().navigate(destination)
        }

        binding.rvPersonajeFavorites.adapter = adapter

        viewModel.favorites.observe(requireActivity()){
            adapter.personajes = it
            adapter.notifyDataSetChanged()
        }
        viewModel.getFavorites()
    }
}