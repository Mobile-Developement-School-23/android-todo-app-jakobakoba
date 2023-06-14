package com.bor96dev.feature.items_impl.di

import com.bor96dev.core.di.FeatureDependencyProvider
import com.bor96dev.core.di.PerFeature
import com.bor96dev.yandextodoapp.core.feature.todo_items_api.TodoItemsApiProvider
import com.bor96dev.yandextodoapp.core.feature.todo_items_api.domain.TodoItemsInteractor
import dagger.Module
import dagger.Provides

@Module
internal class ItemsModule {

    @PerFeature
    @Provides
    fun provideTodoItemsInteractor(
        featureProvider: FeatureDependencyProvider
    ): TodoItemsInteractor {
        return (featureProvider as TodoItemsApiProvider).todoItemsApi().todoItemsInteractor()
    }

}