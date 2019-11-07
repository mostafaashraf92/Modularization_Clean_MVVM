package com.starwars.charactersearch.presentation.characterdetails.planets

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.starwars.domain.usecase.GetPlanetUseCase
import com.starwars.domain.entities.Output
import com.starwars.domain.entities.PlanetModel
import com.starwars.core.viewmodel.BaseViewModel
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent

class PlanetsViewModel(private val useCase: GetPlanetUseCase) :
    BaseViewModel<PlanetModel>(useCase),
    KoinComponent {

     var showEmptyListMessage: MutableLiveData<Boolean> = MutableLiveData()

    init {

    }

     fun executeUseCase(query: String?) {
        viewModelScope.launch {
            val url = urlParseHelper.getUrlFromString(query)
            when (val result = query?.let { useCase.search(url) }) {
                is Output.Success -> handleSuccess(result.output)
                is Output.Error -> handleError(result.exception)
            }
        }
    }

    fun search(characters: String?) {
        showEmptyListMessage.value=false
        executeUseCase(characters)
    }

    override fun handleSuccess(response: PlanetModel?) {
        responseLiveData.value=response
    }

    override fun handleError(errorModel: com.starwars.domain.entities.ErrorModel?) {
        errorLiveData.postValue(errorModel)
        showEmptyListMessage.value=false
    }


}