package com.bor96dev.core.di.navigation

import kotlinx.coroutines.flow.Flow

interface RootNavigation {

    suspend fun navigate(screen: Screen)

    fun listenScreen(): Flow<Screen>
}