package com.miguel.disney.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.miguel.disney.R
import com.miguel.disney.databinding.FragmentPersonajeDetailBinding
import com.miguel.disney.model.Personaje

class PersonajeDetailFragment : Fragment() {

    private lateinit var binding: FragmentPersonajeDetailBinding
    private val args: PersonajeDetailFragmentArgs by navArgs()
    private lateinit var personaje: Personaje
    private lateinit var viewModel: PersonajeDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        personaje = args.personaje
        viewModel = ViewModelProvider(requireActivity())[PersonajeDetailViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //
        binding = FragmentPersonajeDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.txtName.text = personaje.name
        binding.txtPelicula.text = personaje.films.joinToString(" | ")
        if(personaje.isFavorite) {
            binding.btnAddFavorite.visibility = View.GONE
        }
        binding.btnAddFavorite.setOnClickListener {
            viewModel.addFavorites(personaje)
            Snackbar.make(binding.root, "Agregado a favoritos", Snackbar.LENGTH_SHORT).show()
        }
    }
}