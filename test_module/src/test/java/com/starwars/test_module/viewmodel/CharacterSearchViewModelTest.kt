package com.starwars.test_module.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.starwars.charactersearch.presentation.charactersearch.CharacterSearchViewModel
import com.starwars.domain.entities.CharacterModel
import com.starwars.domain.entities.CharacterSearchModel
import com.starwars.domain.entities.ErrorModel
import com.starwars.domain.entities.Output
import com.starwars.domain.usecase.SearchCharacterUseCase
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.any

class CharacterSearchViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
    }

    @Test
    fun executeUseCaseSuccessFully() {
        val useCase = mockk<SearchCharacterUseCase>()

        every { runBlocking { useCase.searchForCharacters(any()) } } returns Output.Success<CharacterSearchModel>(
            constructCharacterSearchModel()
        )
        var viewModel = CharacterSearchViewModel(useCase)
        viewModel.coroutineScope = CoroutineScope(Dispatchers.Unconfined)
        viewModel.afterCharacterChanged(any())
        val expected = viewModel.responseLiveData.value
        Assert.assertNotNull(expected)

    }

    private fun constructCharacterSearchModel(): CharacterSearchModel {
        var characterModel1 = CharacterModel(
            "mostafa",
            "198",
            "",
            "",
            "",
            "",
            "",
            null,
            null
        )
        var characterModel2 = CharacterModel(
            "jack",
            "180",
            "",
            "",
            "",
            "",
            "",
            null,
            null
        )
        var arrayList = ArrayList<CharacterModel>()
        arrayList.add(characterModel1)
        arrayList.add(characterModel2)
        return CharacterSearchModel(2, arrayList)
    }

    private fun constructErrorModel(): ErrorModel {
        return ErrorModel("500", "error")
    }

}