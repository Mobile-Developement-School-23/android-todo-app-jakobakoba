package com.bor96dev.feature.create_impl.di

import com.bor96dev.core.di.FeatureDependency
import com.bor96dev.core.di.PerFeature
import com.bor96dev.yandextodoapp.core.feature.todo_items_api.TodoItemsApiProvider
import com.bor96dev.yandextodoapp.core.feature.todo_items_api.domain.TodoItemsInteractor
import dagger.Module
import dagger.Provides

@Module
internal class CreateModule {
    @PerFeature
    @Provides
    fun provideTodoItemsInteractor(
        featureProvider: FeatureDependency
    ): TodoItemsInteractor {
        return (featureProvider as TodoItemsApiProvider).todoItemsApi().todoItemsInteractor()
    }
}