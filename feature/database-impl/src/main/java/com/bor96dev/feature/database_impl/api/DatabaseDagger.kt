package com.bor96dev.feature.database_impl.api

import com.bor96dev.feature.database_api.DatabaseApi
import com.bor96dev.feature.database_impl.di.DaggerDatabaseComponent

fun getDatabaseApi(): DatabaseApi = DaggerDatabaseComponent.factory().create()