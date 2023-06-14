package com.bor96dev.yandextodoapp.core.feature.todo_items_impl.api

import com.bor96dev.yandextodoapp.core.feature.todo_items_api.TodoItemsApi
import com.bor96dev.yandextodoapp.core.feature.todo_items_impl.di.DaggerTodoItemsComponent

object TodoItemsImpl {

    fun getApi(): TodoItemsApi = DaggerTodoItemsComponent.create()
}