package di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.starwars.data.datasource.CharacterFilmApi
import com.starwars.data.datasource.CharacterPlanetApi
import com.starwars.data.datasource.CharacterSearchApi
import com.starwars.data.datasource.CharacterSpeciesApi
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataSourceModule = module {
    single { provideRetrofit() }
    single { get<Retrofit>().create(CharacterSearchApi::class.java) }
    single { get<Retrofit>().create(CharacterSpeciesApi::class.java) }
    single { get<Retrofit>().create(CharacterFilmApi::class.java) }
    single { get<Retrofit>().create(CharacterPlanetApi::class.java) }

}

    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://swapi.co/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

