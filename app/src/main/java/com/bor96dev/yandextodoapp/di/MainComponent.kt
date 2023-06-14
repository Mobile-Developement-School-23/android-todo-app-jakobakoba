package com.bor96dev.yandextodoapp.di

import com.bor96dev.core.di.CoreDependencies
import com.bor96dev.core.di.FeatureDependencyProvider
import com.bor96dev.core.di.PerFeature
import com.bor96dev.yandextodoapp.MainFragment
import com.bor96dev.yandextodoapp.core.feature.todo_items_api.TodoItemsApiProvider
import dagger.Component

@PerFeature
@Component(
    modules = [
        FeaturesDependenciesModule::class,
        CoreDependenciesModule::class,
    ],
)
internal interface MainComponent :
    CoreDependencies,
    TodoItemsApiProvider {

    fun inject(fragment: MainFragment)
}