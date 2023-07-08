package com.bor96dev.feature.database_api

import com.bor96dev.core.di.FeatureDependency

interface DatabaseApiProvider : FeatureDependency {
    fun databaseApi(): DatabaseApi
}