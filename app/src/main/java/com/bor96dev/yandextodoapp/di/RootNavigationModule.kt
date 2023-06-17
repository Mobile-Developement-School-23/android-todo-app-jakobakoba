package com.bor96dev.yandextodoapp.di

import com.bor96dev.core.di.PerFeature
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Cicerone.Companion.create
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides

@Module
internal class RootNavigationModule {

    private val cicerone: Cicerone<Router> = create()

    @Provides
    @PerFeature
    fun provideRouter(): Router {
        return cicerone.router
    }

    @Provides
    @PerFeature
    fun provideNavigatorHolder(): NavigatorHolder {
        return cicerone.getNavigatorHolder()
    }
}