package com.bor96dev.feature.database_impl.api

import com.bor96dev.core.di.ContextProvider
import com.bor96dev.feature.database_api.DatabaseApi
import com.bor96dev.feature.database_impl.di.DaggerDatabaseComponent

fun getDatabaseApi(
    contextProvider: ContextProvider
): DatabaseApi = DaggerDatabaseComponent.factory().create(contextProvider)