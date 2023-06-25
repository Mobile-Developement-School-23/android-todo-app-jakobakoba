package com.bor96dev.yandextodoapp.core.feature.todo_items_impl.api

import com.bor96dev.feature.repository_todo_items_api.RepositoryTodoItemsApi
import com.bor96dev.yandextodoapp.core.feature.todo_items_api.TodoItemsApi
import com.bor96dev.yandextodoapp.core.feature.todo_items_impl.di.DaggerTodoItemsComponent

object TodoItemsImpl {
    fun getApi(api: RepositoryTodoItemsApi): TodoItemsApi =
        DaggerTodoItemsComponent.factory().create(api)
}