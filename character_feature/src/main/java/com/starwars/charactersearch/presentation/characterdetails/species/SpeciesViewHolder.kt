package com.starwars.charactersearch.presentation.characterdetails.species

import android.view.View
import com.starwars.charactersearch.databinding.SpeciesViewHolderBinding
import com.starwars.core.view.BaseViewHolder
import com.starwars.domain.entities.SpeciesModel

class SpeciesViewHolder(view: View, var binding: SpeciesViewHolderBinding) :
    BaseViewHolder<SpeciesModel>(view, binding) {
    override fun bind(item: SpeciesModel) {
        binding.model = item
    }
}