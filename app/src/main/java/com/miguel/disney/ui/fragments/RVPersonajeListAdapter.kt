package com.miguel.disney.ui.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.miguel.disney.databinding.ItemPersonajeBinding
import com.miguel.disney.model.Personaje

class RVPersonajeListAdapter(var personajes: List<Personaje>, val onPersonajeClick: (Personaje) -> Unit) : RecyclerView.Adapter<PersonajeVH>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonajeVH {
        val binding = ItemPersonajeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PersonajeVH(binding,onPersonajeClick)
    }

    override fun getItemCount(): Int = personajes.size

    override fun onBindViewHolder(holder: PersonajeVH, position: Int) {
        holder.bind(personajes[position])
    }
}

class PersonajeVH(private val binding: ItemPersonajeBinding, val onNoteClick: (Personaje) -> Unit): RecyclerView.ViewHolder(binding.root){
    fun bind(personaje: Personaje){
        binding.txtNombre.text = personaje.name
        binding.txtFilms.text = personaje.films.joinToString(" | ")
        binding.txtUrl.text = personaje.url
        binding.root.setOnClickListener {
            onNoteClick(personaje)
        }
    }
}