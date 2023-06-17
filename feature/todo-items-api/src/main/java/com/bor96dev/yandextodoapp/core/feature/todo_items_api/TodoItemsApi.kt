package com.bor96dev.yandextodoapp.core.feature.todo_items_api

import com.bor96dev.yandextodoapp.core.feature.todo_items_api.domain.TodoItemsInteractor

interface TodoItemsApi {
    fun todoItemsInteractor(): TodoItemsInteractor
}