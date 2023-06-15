package com.bor96dev.feature.create_api

import com.bor96dev.core.di.FeatureDependency

interface CreateApiProvider : FeatureDependency {

    fun createApi(): CreateApi
}