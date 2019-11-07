package com.starwars.charactersearch.presentation.characterdetails.species

import android.view.LayoutInflater
import android.view.ViewGroup
import com.starwars.charactersearch.databinding.FilmViewHolderBinding
import com.starwars.core.view.BaseAdapter
import com.starwars.core.view.BaseViewHolder
import com.starwars.domain.entities.FilmModel

class FilmsAdapter : BaseAdapter<BaseViewHolder<FilmModel>, FilmModel>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<FilmModel> {
        val binding = FilmViewHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FilmViewHolder(binding.root, binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<FilmModel>, position: Int) {
        val drawerItem = items?.get(position)
        drawerItem?.let { holder.bind(it) }
    }
}