package com.starwars.charactersearch.presentation.charactersearch

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

import com.starwars.charactersearch.databinding.CharacterItemBinding
import com.starwars.core.interfaces.IOnCharacterClickedListener
import com.starwars.domain.entities.CharacterModel
import org.koin.core.KoinComponent

class CharactersSearchAdapter :
    RecyclerView.Adapter<CharactersSearchAdapter.ItemViewHolder>(),
    KoinComponent {
    override fun getItemCount(): Int {
        return list.size
    }

    private var list: ArrayList<CharacterModel> = ArrayList()

    fun setAllItems(items: ArrayList<CharacterModel>) {
        list = items
        notifyDataSetChanged()
    }

    companion object {
        var onItemClickedListener: IOnCharacterClickedListener<CharacterModel>? = null
        @JvmStatic
        @BindingAdapter("app:setItemClickListener")
        fun setItemClickListener(
            recyclerView: RecyclerView,
            listener: IOnCharacterClickedListener<CharacterModel>
        ) {
            onItemClickedListener = listener
        }

    }

    fun onItemClicked(view: View, model: CharacterModel) {
        onItemClickedListener?.onListItemClicked(model)

    }


    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ItemViewHolder {
        val binding =
            CharacterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(
            binding,
            this@CharactersSearchAdapter
        )
    }


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val characterModel = list[position]
        holder.bind(characterModel)

    }

    class ItemViewHolder constructor(
        private val binding: CharacterItemBinding,
        private val adapter: CharactersSearchAdapter
    ) : RecyclerView.ViewHolder(binding.root), KoinComponent {
        fun bind(videoItem: CharacterModel) {
            binding.model = videoItem
            binding.adapter = adapter
        }
    }

}

