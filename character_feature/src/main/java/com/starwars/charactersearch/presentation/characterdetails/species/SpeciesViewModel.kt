package com.starwars.charactersearch.presentation.characterdetails.species

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.starwars.domain.usecase.GetSpeciesUseCase
import com.starwars.domain.entities.ErrorModel
import com.starwars.domain.entities.Output
import com.starwars.domain.entities.SpeciesModel
import com.starwars.core.viewmodel.BaseDetailsViewModel
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent

class SpeciesViewModel(private val useCase: GetSpeciesUseCase) :
    BaseDetailsViewModel<SpeciesModel>(useCase),
    KoinComponent {

    var showEmptyListMessage: MutableLiveData<Boolean> = MutableLiveData()
    var result: ArrayList<SpeciesModel> = ArrayList()

    init {

    }

    override fun executeUseCase(array: ArrayList<String>) {
        viewModelScope.launch {
            array.forEach {
                val url = urlParseHelper.getUrlFromString(it)
                when (val result = useCase.search(url)) {
                    is Output.Success -> handleSuccess(result.output)
                    is Output.Error -> handleError(result.exception)
                }
            }
        }
    }

    override fun searchDetails(array: ArrayList<String>) {
        if (shouldLoadNewData()) {
            result.clear()
            showEmptyListMessage.value = false
            executeUseCase(array)
        }
    }

    override fun handleSuccess(response: SpeciesModel?) {
        response?.let {
            result.add(it)
        }
        responseLiveData.value = result
    }

    override fun handleError(errorModel: ErrorModel?) {
        errorLiveData.postValue(errorModel)
        showEmptyListMessage.value = false
    }


}