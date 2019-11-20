package com.starwars.core.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.starwars.domain.entities.CharacterModel
import com.starwars.domain.entities.CharacterSearchModel
import com.starwars.domain.entities.ErrorModel
import com.starwars.domain.entities.Output
import com.starwars.domain.repository.CharacterSearchRepo
import com.starwars.domain.usecase.SearchCharacterUseCase
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SearchCharacterUseCaseTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
    }

    @Test
    fun searchForCharactersSuccess() {
        runBlocking {

            val repo = mockk<CharacterSearchRepo>()
            every { runBlocking { repo.searchForCharacter(any()) } } returns Output.Success<CharacterSearchModel>(
                constructCharacterSearchModel()
            )

            var useCase = SearchCharacterUseCase(repo)
            val expected =
                useCase.searchForCharacters("") as Output.Success<CharacterSearchModel?>
            Assert.assertNotNull(expected)
            Assert.assertEquals(expected.output?.results?.size, 2)
        }

    }

    @Test
    fun searchForCharactersError() {
        runBlocking {

            val repo = mockk<CharacterSearchRepo>()
            every { runBlocking { repo.searchForCharacter(any()) } } returns Output.Error(
                constructErrorModel()
            )

            var useCase = SearchCharacterUseCase(repo)
            val expected =
                useCase.searchForCharacters("") as Output.Error
            Assert.assertNotNull(expected.exception)
        }

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