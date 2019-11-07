package com.starwars.charactersearch

import repositories.CharactersSpeciesRepoImp
import repositories.CharacterSearchRepoImp
import repositories.CharactersFilmsRepoImp
import repositories.CharactersPlanetRepoImp
import com.starwars.domain.usecase.GetFilmsUseCase
import com.starwars.domain.usecase.GetPlanetUseCase
import com.starwars.domain.usecase.GetSpeciesUseCase
import com.starwars.domain.usecase.SearchCharacterUseCase
import com.starwars.charactersearch.presentation.characterdetails.CharacterDetailsViewModel
import com.starwars.charactersearch.presentation.characterdetails.films.FilmDetailsViewModel
import com.starwars.charactersearch.presentation.characterdetails.planets.PlanetsViewModel
import com.starwars.charactersearch.presentation.characterdetails.species.FilmsAdapter
import com.starwars.charactersearch.presentation.characterdetails.species.SpeciesAdapter
import com.starwars.charactersearch.presentation.characterdetails.species.SpeciesViewModel
import com.starwars.charactersearch.presentation.charactersearch.CharacterSearchViewModel
import com.starwars.charactersearch.presentation.charactersearch.CharactersSearchAdapter
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val characterFeatureUiModule = module {
    factory { CharactersSearchAdapter() }
    factory { FilmsAdapter() }
    factory { SpeciesAdapter() }
}
val repositoryModule = module {
    factory { repositories.CharacterSearchRepoImp(get()) }
    factory { repositories.CharactersSpeciesRepoImp(get()) }
    factory { repositories.CharactersFilmsRepoImp(get()) }
    factory { repositories.CharactersPlanetRepoImp(get()) }
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





