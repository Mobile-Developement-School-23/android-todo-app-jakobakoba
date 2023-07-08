package com.bor96dev.feature.database_impl.di

import com.bor96dev.core.di.ContextProvider
import com.bor96dev.core.di.PerFeature
import com.bor96dev.feature.database_api.DatabaseApi
import dagger.Component

@PerFeature
@Component(
    modules = [
        DatabaseModule::class
    ],
    dependencies = [
        ContextProvider::class
    ]
)
internal interface DatabaseComponent : DatabaseApi {

    @Component.Factory
    interface Builder {
        fun create(contextProvider: ContextProvider): DatabaseComponent
    }
}