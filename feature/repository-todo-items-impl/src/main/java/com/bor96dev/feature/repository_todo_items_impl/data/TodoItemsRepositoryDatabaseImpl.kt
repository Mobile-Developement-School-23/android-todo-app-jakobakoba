package com.bor96dev.feature.repository_todo_items_impl.data

import com.bor96dev.feature.database_api.DatabaseRepository
import com.bor96dev.feature.repository_todo_items_api.TodoItemsRepository
import com.bor96dev.yandextodoapp.core.feature.todo_items_api.domain.TodoItem
import javax.inject.Inject

internal class TodoItemsRepositoryDatabaseImpl @Inject constructor(
    private val databaseRepository: DatabaseRepository,
) : TodoItemsRepository {

    override suspend fun getList(): List<TodoItem> {
        return listOf(TodoItem.EMPTY)
    }

    override suspend fun addElement(name: String) {

    }

    override suspend fun getElement(id: String): TodoItem {
        return TodoItem.EMPTY
    }

    override suspend fun deleteElement(id: String) {

    }

    override suspend fun updateElement(todoItem: TodoItem) {

    }
}