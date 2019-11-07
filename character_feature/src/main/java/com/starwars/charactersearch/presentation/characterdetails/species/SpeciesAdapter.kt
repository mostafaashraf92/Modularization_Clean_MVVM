package com.starwars.charactersearch.presentation.characterdetails.species

import android.view.LayoutInflater
import android.view.ViewGroup
import com.starwars.charactersearch.databinding.SpeciesViewHolderBinding
import com.starwars.core.view.BaseAdapter
import com.starwars.core.view.BaseViewHolder
import com.starwars.domain.entities.SpeciesModel

class SpeciesAdapter : BaseAdapter<BaseViewHolder<SpeciesModel>, SpeciesModel>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<SpeciesModel> {
        val binding = SpeciesViewHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SpeciesViewHolder(binding.root, binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<SpeciesModel>, position: Int) {
        val drawerItem = items?.get(position)
        drawerItem?.let { holder.bind(it) }
    }
}