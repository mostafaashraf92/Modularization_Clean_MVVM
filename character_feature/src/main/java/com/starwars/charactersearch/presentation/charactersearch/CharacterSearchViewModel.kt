package com.starwars.charactersearch.presentation.charactersearch

import androidx.lifecycle.MutableLiveData
import com.starwars.core.viewmodel.BaseViewModel
import com.starwars.domain.entities.CharacterSearchModel
import com.starwars.domain.entities.Output
import com.starwars.domain.usecase.SearchCharacterUseCase
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent

class CharacterSearchViewModel(private val useCase: SearchCharacterUseCase) :
    BaseViewModel<CharacterSearchModel>(useCase),
    KoinComponent {

    var showEmptyListMessage: MutableLiveData<Boolean> = MutableLiveData()

    init {

    }

    private fun executeUseCase(query: String?) {
        coroutineScope.launch {
            when (val result = useCase.searchForCharacters(query)) {
                is Output.Success -> handleSuccess(result.output)
                is Output.Error -> handleError(result.exception)
            }
        }
    }

    fun afterCharacterChanged(characters: String?) {
        showEmptyListMessage.value = false
        executeUseCase(characters)
    }

    override fun handleSuccess(response: CharacterSearchModel?) {
        responseLiveData.postValue(response)
        showEmptyListMessage.value = shouldShowEmptyMessage(response)
    }

    override fun handleError(errorModel: com.starwars.domain.entities.ErrorModel?) {
        errorLiveData.postValue(errorModel)
        showEmptyListMessage.value = false
    }

    private fun shouldShowEmptyMessage(response: CharacterSearchModel?) =
        !(response?.results != null && response.results?.size!! > 0)

}