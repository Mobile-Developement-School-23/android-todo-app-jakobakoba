package com.bor96dev.yandextodoapp.di

import com.bor96dev.core.di.PerFeature
import com.bor96dev.feature.create_api.CreateApi
import com.bor96dev.feature.create_impl.api.CreateImpl
import com.bor96dev.feature.items_api.ItemsApi
import com.bor96dev.feature.items_impl.api.ItemsImpl
import com.bor96dev.feature.repository_todo_items_api.RepositoryTodoItemsApi
import com.bor96dev.feature.repository_todo_items_impl.api.getRepositoryTodoItemsApi
import com.bor96dev.yandextodoapp.core.feature.todo_items_api.TodoItemsApi
import com.bor96dev.yandextodoapp.core.feature.todo_items_impl.api.TodoItemsImpl
import dagger.Module
import dagger.Provides

@Module
internal class FeaturesDependenciesModule {

    @Provides
    @PerFeature
    fun provideItemsApi(): ItemsApi = ItemsImpl()

    @Provides
    @PerFeature
    fun provideCreateApi(): CreateApi = CreateImpl()

    @Provides
    @PerFeature
    fun provideTodoItemsApi(api: RepositoryTodoItemsApi): TodoItemsApi = TodoItemsImpl.getApi(api)

    @Provides
    @PerFeature
    fun provideRepositoryTodoItemsApi(): RepositoryTodoItemsApi = getRepositoryTodoItemsApi()
}