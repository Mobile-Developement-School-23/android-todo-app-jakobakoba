package com.bor96dev.feature.repository_todo_items_impl.api

import com.bor96dev.feature.repository_todo_items_api.RepositoryTodoItemsApi
import com.bor96dev.feature.repository_todo_items_impl.di.DaggerTodoItemsComponent


fun getRepositoryTodoItemsApi(): RepositoryTodoItemsApi {
    return DaggerTodoItemsComponent.create()
}