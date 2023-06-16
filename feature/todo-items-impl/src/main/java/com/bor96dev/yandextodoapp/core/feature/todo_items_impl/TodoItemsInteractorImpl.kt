package com.bor96dev.yandextodoapp.core.feature.todo_items_impl

import com.bor96dev.yandextodoapp.core.feature.todo_items_api.domain.TodoItem
import com.bor96dev.yandextodoapp.core.feature.todo_items_api.domain.TodoItemPriority
import com.bor96dev.yandextodoapp.core.feature.todo_items_api.domain.TodoItemsInteractor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

internal class TodoItemsInteractorImpl @Inject constructor() : TodoItemsInteractor {

    private val todoItems: MutableStateFlow<List<TodoItem>> = MutableStateFlow(
        getMockItems()
    )

    override suspend fun addItem(
        text: String,
        priority: TodoItemPriority
    ) {
        val list = todoItems.value.toMutableList()
        list.add(
            TodoItem(
                System.currentTimeMillis().toString(),
                text,
                priority
            )
        )
        todoItems.emit(list)
    }

    override fun getItems(): Flow<List<TodoItem>> = todoItems

    private fun getMockItems(): List<TodoItem> {
        return listOf(
            TodoItem("1", "1", TodoItemPriority.LOW),
        )
    }
}