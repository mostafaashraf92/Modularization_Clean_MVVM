package com.starwars.core.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.starwars.core.UrlParseHelper
import com.starwars.domain.entities.ErrorModel
import com.starwars.domain.usecase.UseCase
import org.koin.core.KoinComponent
import org.koin.core.inject


abstract class BaseDetailsViewModel<T>(useCase: UseCase<T>) : ViewModel(), KoinComponent {
    var responseLiveData: MutableLiveData<ArrayList<T>?> = MutableLiveData()
    var errorLiveData: MutableLiveData<ErrorModel> = MutableLiveData()
    protected abstract fun handleSuccess(response: T?)
    protected abstract fun handleError(errorModel: ErrorModel?)
    val urlParseHelper: UrlParseHelper by inject()
    abstract fun executeUseCase(array: ArrayList<String>)
    abstract fun searchDetails(array: ArrayList<String>)
    fun shouldLoadNewData() = !(responseLiveData.value != null && responseLiveData.value!!.size > 0)

}