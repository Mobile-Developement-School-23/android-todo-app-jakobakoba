package com.bor96dev.yandextodoapp.core.feature.todo_items_impl

import com.bor96dev.yandextodoapp.core.feature.todo_items_api.domain.TodoItem
import com.bor96dev.yandextodoapp.core.feature.todo_items_api.domain.TodoItemsInteractor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

internal class TodoItemsInteractorImpl @Inject constructor() : TodoItemsInteractor {

    private val todoItems: MutableStateFlow<List<TodoItem>> = MutableStateFlow(emptyList())

    override suspend fun addItem(item: TodoItem) {
        val list = todoItems.value.toMutableList()
        list.add(item)
        todoItems.emit(list)
    }

    override fun getItems(): Flow<List<TodoItem>> = todoItems
}