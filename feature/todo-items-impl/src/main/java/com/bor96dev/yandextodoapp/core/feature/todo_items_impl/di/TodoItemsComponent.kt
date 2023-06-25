package com.bor96dev.yandextodoapp.core.feature.todo_items_impl.di

import com.bor96dev.core.di.PerFeature
import com.bor96dev.feature.repository_todo_items_api.RepositoryTodoItemsApi
import com.bor96dev.yandextodoapp.core.feature.todo_items_api.TodoItemsApi
import dagger.Component

@Component(
    dependencies = [
        RepositoryTodoItemsApi::class
    ],
    modules = [
        TodoItemsModule::class,
    ]
)
@PerFeature
internal interface TodoItemsComponent : TodoItemsApi {
    @Component.Factory
    interface Builder {
        fun create(
            api: RepositoryTodoItemsApi,
        ): TodoItemsComponent
    }
}