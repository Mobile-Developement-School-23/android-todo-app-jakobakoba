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
                priority,
                false
            )
        )
        todoItems.emit(list)
    }

    override suspend fun makeIsDone(id: String, isDone: Boolean) {
        val list = todoItems.value.toMutableList()
        val itemPosition = list.indexOfFirst { it.id == id }
        list[itemPosition] = list[itemPosition].copy(isDone = isDone)
        todoItems.emit(list)
    }

    override fun getItem(id: String): TodoItem {
        return todoItems.value.find { it.id == id }!!
    }

    override suspend fun removeItem(id: String) {
        val array = todoItems.value.filter { it.id != id }
        todoItems.emit(array)
    }

    override fun getItems(): Flow<List<TodoItem>> = todoItems

    private fun getMockItems(): List<TodoItem> {
        return listOf(
            TodoItem("1", "1", TodoItemPriority.LOW, false),
        )
    }
}