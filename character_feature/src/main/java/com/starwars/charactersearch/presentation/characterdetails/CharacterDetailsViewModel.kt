package com.starwars.charactersearch.presentation.characterdetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.starwars.domain.entities.CharacterModel
import org.koin.core.KoinComponent

class CharacterDetailsViewModel : ViewModel(), KoinComponent {

    var characterModelLiveData: MutableLiveData<CharacterModel?> = MutableLiveData()


    fun setData(characterModel: CharacterModel?) {
        characterModelLiveData.value = characterModel
    }


}