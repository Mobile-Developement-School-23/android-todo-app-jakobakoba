package com.bor96dev.yandextodoapp.di

import com.bor96dev.core.di.PerFeature
import com.bor96dev.core.di.navigation.RootNavigation
import com.bor96dev.yandextodoapp.navigation.RootNavigationImpl
import dagger.Module
import dagger.Provides

@Module
internal class CoreDependenciesModule {

    @PerFeature
    @Provides
    fun provideRootNavigation(): RootNavigation = RootNavigationImpl()
}