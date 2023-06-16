package com.bor96dev.yandextodoapp.core.feature.todo_items_impl.di

import com.bor96dev.core.di.PerFeature
import com.bor96dev.yandextodoapp.core.feature.todo_items_api.domain.TodoItemsInteractor
import com.bor96dev.yandextodoapp.core.feature.todo_items_impl.TodoItemsInteractorImpl
import dagger.Module
import dagger.Provides

@Module
internal class TodoItemsModule {
    @PerFeature
    @Provides
    fun provideTodoItemsInteractor(impl: TodoItemsInteractorImpl): TodoItemsInteractor = impl
}