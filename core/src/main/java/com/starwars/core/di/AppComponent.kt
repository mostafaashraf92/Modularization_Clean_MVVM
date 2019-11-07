package com.starwars.core.di

import com.starwars.core.ApiPaths
import com.starwars.core.Constants
import com.starwars.core.UrlParseHelper
import org.koin.dsl.module

val coreModules = module {
    single { ApiPaths() }
    single { Constants() }
    single { UrlParseHelper() }
}
