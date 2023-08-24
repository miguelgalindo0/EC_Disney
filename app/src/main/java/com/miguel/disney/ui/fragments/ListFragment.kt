package com.miguel.disney.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.miguel.disney.databinding.FragmentListBinding
import com.miguel.disney.ui.viewmodel.PersonajeListViewModel

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private lateinit var viewModel: PersonajeListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[PersonajeListViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = RVPersonajeListAdapter(listOf()) {personaje ->
            val destination = ListFragmentDirections.actionListFragmentToPersonajeDetailFragment(personaje)
            findNavController().navigate(destination)
        }
        binding.rvPersonajeList.adapter = adapter

        viewModel
    }
}