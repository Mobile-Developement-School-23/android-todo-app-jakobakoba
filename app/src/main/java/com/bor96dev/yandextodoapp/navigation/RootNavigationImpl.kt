package com.bor96dev.yandextodoapp.navigation

import com.bor96dev.core.di.navigation.RootNavigation
import com.bor96dev.core.di.navigation.Screen
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

internal class RootNavigationImpl : RootNavigation {

    private val currentScreen = Channel<Screen>()

    override suspend fun navigate(screen: Screen) {
        currentScreen.send(screen)
    }

    override fun listenScreen(): Flow<Screen> = currentScreen.receiveAsFlow()
}