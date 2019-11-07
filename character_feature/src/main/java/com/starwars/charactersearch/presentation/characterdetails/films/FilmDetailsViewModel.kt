package com.starwars.charactersearch.presentation.characterdetails.films

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.starwars.core.viewmodel.BaseDetailsViewModel
import com.starwars.domain.entities.Output
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent

class FilmDetailsViewModel(private val useCase: com.starwars.domain.usecase.GetFilmsUseCase) :
    BaseDetailsViewModel<com.starwars.domain.entities.FilmModel>(useCase),
    KoinComponent {

    var showEmptyListMessage: MutableLiveData<Boolean> = MutableLiveData()
    var result: ArrayList<com.starwars.domain.entities.FilmModel> = ArrayList()


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

    override fun handleSuccess(response: com.starwars.domain.entities.FilmModel?) {
        response?.let {
            result.add(it)
        }
        responseLiveData.value = result
    }

    override fun handleError(errorModel: com.starwars.domain.entities.ErrorModel?) {
        errorLiveData.postValue(errorModel)
        showEmptyListMessage.value = false
    }


}