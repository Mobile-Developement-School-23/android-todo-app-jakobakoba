package com.bor96dev.feature.repository_todo_items_impl.data

import com.bor96dev.feature.database_api.DatabaseRepository
import com.bor96dev.feature.repository_todo_items_api.TodoItemsRepository
import com.bor96dev.feature.repository_todo_items_impl.data.mapper.toDomain
import com.bor96dev.yandextodoapp.core.feature.todo_items_api.domain.TodoItem
import java.util.UUID
import javax.inject.Inject

internal class TodoItemsRepositoryDatabaseImpl @Inject constructor(
    private val databaseRepository: DatabaseRepository,
) : TodoItemsRepository {

    override suspend fun getList(): List<TodoItem> {
        return databaseRepository.getItems().map { it.toDomain() }
    }

    override suspend fun addElement(name: String) {
        val uid = UUID.randomUUID().toString()
        databaseRepository.addElement(uid, name)
    }

    override suspend fun getElement(id: String): TodoItem {
        return TodoItem.EMPTY
    }

    override suspend fun deleteElement(id: String) {

    }

    override suspend fun updateElement(todoItem: TodoItem) {

    }
}