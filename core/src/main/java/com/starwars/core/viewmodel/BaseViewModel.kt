package com.starwars.core.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.starwars.core.UrlParseHelper
import com.starwars.domain.entities.ErrorModel
import com.starwars.domain.usecase.UseCase
import kotlinx.coroutines.CoroutineScope
import org.koin.core.KoinComponent
import org.koin.core.inject


abstract class BaseViewModel<T>(useCase: UseCase<T>) : ViewModel(), KoinComponent {
    var responseLiveData: MutableLiveData<T> = MutableLiveData()
    var errorLiveData: MutableLiveData<ErrorModel> = MutableLiveData()
    var coroutineScope: CoroutineScope = viewModelScope
    val urlParseHelper: UrlParseHelper by inject()
    protected abstract fun handleSuccess(response: T?)
    protected abstract fun handleError(errorModel: ErrorModel?)

}