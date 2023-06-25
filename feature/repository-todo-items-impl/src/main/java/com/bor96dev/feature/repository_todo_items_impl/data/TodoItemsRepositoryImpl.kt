package com.bor96dev.feature.repository_todo_items_impl.data

import com.bor96dev.feature.repository_todo_items_api.TodoItemsRepository
import com.bor96dev.yandextodoapp.core.feature.todo_items_api.domain.TodoItem
import javax.inject.Inject

internal class TodoItemsRepositoryImpl @Inject constructor(
    private val todoItemsApi: TodoItemsApi,
) : TodoItemsRepository {

    private var revision = 0L

    override suspend fun getList(): List<TodoItem> {
        val response = todoItemsApi.getList()
        revision = response.revision

        return response.list.map { it.toDomain() }
    }
}