package com.starwars.app

import android.app.Application
import com.starwars.charactersearch.characterFeatureData
import com.starwars.charactersearch.characterFeatureUiModule
import com.starwars.charactersearch.useCaseModule
import com.starwars.charactersearch.viewModelModule
import com.starwars.core.di.coreModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@BaseApplication)
            androidFileProperties()
            modules(
                listOf(
                    characterFeatureUiModule,
                    coreModules,
                    characterFeatureData,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}