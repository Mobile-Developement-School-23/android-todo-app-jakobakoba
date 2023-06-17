package com.bor96dev.yandextodoapp.di

import com.bor96dev.core.di.CoreDependencies
import com.bor96dev.core.di.PerFeature
import com.bor96dev.feature.create_api.CreateApiProvider
import com.bor96dev.yandextodoapp.MainActivity
import com.bor96dev.yandextodoapp.core.feature.todo_items_api.TodoItemsApiProvider
import dagger.Component

@PerFeature
@Component(
    modules = [
        FeaturesDependenciesModule::class,
        RootNavigationModule::class
    ],
)
internal interface MainComponent :
    CoreDependencies,
    TodoItemsApiProvider,
    CreateApiProvider {

    fun inject(activity: MainActivity)
}