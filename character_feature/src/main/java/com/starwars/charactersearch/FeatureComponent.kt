package com.starwars.charactersearch

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.starwars.charactersearch.presentation.characterdetails.CharacterDetailsViewModel
import com.starwars.charactersearch.presentation.characterdetails.films.FilmDetailsViewModel
import com.starwars.charactersearch.presentation.characterdetails.films.data.repo.CharactersFilmsRepoImp
import com.starwars.charactersearch.presentation.characterdetails.films.data.source.CharacterFilmApi
import com.starwars.charactersearch.presentation.characterdetails.films.data.source.CharacterFilmsDataSourceImp
import com.starwars.charactersearch.presentation.characterdetails.planets.PlanetsViewModel
import com.starwars.charactersearch.presentation.characterdetails.planets.data.repo.CharactersPlanetRepoImp
import com.starwars.charactersearch.presentation.characterdetails.planets.data.source.CharacterPlanetApi
import com.starwars.charactersearch.presentation.characterdetails.planets.data.source.CharacterPlanetsDataSourceImp
import com.starwars.charactersearch.presentation.characterdetails.species.FilmsAdapter
import com.starwars.charactersearch.presentation.characterdetails.species.SpeciesAdapter
import com.starwars.charactersearch.presentation.characterdetails.species.SpeciesViewModel
import com.starwars.charactersearch.presentation.characterdetails.species.data.repo.CharactersSpeciesRepoImp
import com.starwars.charactersearch.presentation.characterdetails.species.data.source.CharacterSpeciesApi
import com.starwars.charactersearch.presentation.characterdetails.species.data.source.CharacterSpeciesDataSourceImp
import com.starwars.charactersearch.presentation.charactersearch.CharacterSearchViewModel
import com.starwars.charactersearch.presentation.charactersearch.CharactersSearchAdapter
import com.starwars.charactersearch.presentation.charactersearch.data.repo.CharacterSearchRepoImp
import com.starwars.charactersearch.presentation.charactersearch.data.source.CharacterSearchApi
import com.starwars.charactersearch.presentation.charactersearch.data.source.CharacterSearchDataSourceImp
import com.starwars.domain.usecase.GetFilmsUseCase
import com.starwars.domain.usecase.GetPlanetUseCase
import com.starwars.domain.usecase.GetSpeciesUseCase
import com.starwars.domain.usecase.SearchCharacterUseCase
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import repositories.CharacterSearchDataSource
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val characterFeatureUiModule = module {
    factory { CharactersSearchAdapter() }
    factory { FilmsAdapter() }
    factory { SpeciesAdapter() }
}

val viewModelModule = module {
    viewModel {
        CharacterSearchViewModel(
            get()
        )
    }
    viewModel {
        CharacterDetailsViewModel()
    }
    viewModel {
        FilmDetailsViewModel(get())
    }
    viewModel {
        SpeciesViewModel(get())
    }
    viewModel {
        PlanetsViewModel(get())
    }


}

val useCaseModule = module {
    factory { SearchCharacterUseCase(get<CharacterSearchRepoImp>()) }
    factory { GetSpeciesUseCase(get<CharactersSpeciesRepoImp>()) }
    factory { GetFilmsUseCase(get<CharactersFilmsRepoImp>()) }
    factory { GetPlanetUseCase(get<CharactersPlanetRepoImp>()) }
}

val characterFeatureData = module {
    single { provideRetrofit() }
    single { get<Retrofit>().create(CharacterSearchApi::class.java) }
    single { get<Retrofit>().create(CharacterSpeciesApi::class.java) }
    single { get<Retrofit>().create(CharacterFilmApi::class.java) }
    single { get<Retrofit>().create(CharacterPlanetApi::class.java) }

    factory { CharacterSearchRepoImp(get()) }
    factory { CharactersSpeciesRepoImp(get()) }
    factory { CharactersFilmsRepoImp(get()) }
    factory { CharactersPlanetRepoImp(get()) }

    factory { CharacterFilmsDataSourceImp(get()) }
    factory { CharacterPlanetsDataSourceImp(get()) }
    factory { CharacterSearchDataSourceImp(get()) }
    factory { CharacterSpeciesDataSourceImp(get()) }

}

fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://swapi.co/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
}





