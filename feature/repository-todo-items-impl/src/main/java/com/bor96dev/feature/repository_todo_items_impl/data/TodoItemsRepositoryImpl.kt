package com.bor96dev.feature.repository_todo_items_impl.data

import com.bor96dev.feature.repository_todo_items_api.TodoItemsRepository
import com.bor96dev.feature.repository_todo_items_impl.data.response.AddElementRequest
import com.bor96dev.feature.repository_todo_items_impl.data.dto.TodoDto
import com.bor96dev.feature.repository_todo_items_impl.data.mapper.toData
import com.bor96dev.feature.repository_todo_items_impl.data.mapper.toDomain
import com.bor96dev.yandextodoapp.core.feature.todo_items_api.domain.TodoItem
import java.util.*
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

    override suspend fun addElement(name: String) {
        val time = System.currentTimeMillis()

        val mock = TodoDto.createMock(
            id = UUID.randomUUID().toString(),
            text = name,
            created_at = time,
            changed_at = time
        )

        val request = AddElementRequest(
            element = mock,

            )

        val response = todoItemsApi.addElement(
            revision,
            request
        )

        revision = response.revision
    }

    override suspend fun getElement(id: String): TodoItem {
        val response = todoItemsApi.getElement(id)

        revision = response.revision

        return response.element.toDomain()
    }

    override suspend fun deleteElement(id: String) {
        val response = todoItemsApi.deleteElement(revision, id)

        revision = response.revision
    }

    override suspend fun updateElement(todoItem: TodoItem) {
        val element = todoItem.toData()

        val request = AddElementRequest(element)

        val response = todoItemsApi.updateElement(
            revision,
            element.id,
            request
        )

        revision = response.revision
    }
}