package com.starwars.charactersearch.presentation.characterdetails.species

import android.view.View
import com.starwars.charactersearch.databinding.FilmViewHolderBinding
import com.starwars.core.view.BaseViewHolder
import com.starwars.domain.entities.FilmModel

class FilmViewHolder(view: View, var binding: FilmViewHolderBinding) :
    BaseViewHolder<FilmModel>(view, binding) {
    override fun bind(item: FilmModel) {
        binding.model = item
    }
}