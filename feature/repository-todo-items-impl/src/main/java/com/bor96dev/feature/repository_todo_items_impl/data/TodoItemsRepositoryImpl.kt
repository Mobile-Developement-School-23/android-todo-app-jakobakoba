package com.bor96dev.feature.repository_todo_items_impl.data

import android.util.Log
import com.bor96dev.feature.database_api.DatabaseRepository
import com.bor96dev.feature.repository_todo_items_api.TodoItemsRepository
import com.bor96dev.feature.repository_todo_items_impl.TodoItemData
import com.bor96dev.feature.repository_todo_items_impl.data.dto.TodoDto
import com.bor96dev.feature.repository_todo_items_impl.data.mapper.databaseToData
import com.bor96dev.feature.repository_todo_items_impl.data.mapper.networkToData
import com.bor96dev.feature.repository_todo_items_impl.data.mapper.toData
import com.bor96dev.feature.repository_todo_items_impl.data.mapper.toDomain
import com.bor96dev.feature.repository_todo_items_impl.data.response.AddElementRequest
import com.bor96dev.yandextodoapp.core.feature.todo_items_api.domain.TodoItem
import java.util.*
import javax.inject.Inject


internal class TodoItemsRepositoryImpl @Inject constructor(
    private val todoItemsApi: TodoItemsApi,
    private val databaseRepository: DatabaseRepository,
) : TodoItemsRepository {

    private var revision = 0L

    override suspend fun getList(): List<TodoItem> {
        val list = try {
            val response = todoItemsApi.getList()
            revision = response.revision
            response.list.map { it.toDomain() }
        } catch (e: Exception) {
            databaseRepository.getItems().map { it.toDomain() }
        }

        syncData()

        return list
    }

    private suspend fun syncData() {
        val networkList = try {
            todoItemsApi.getList().list.map { it.networkToData() }
        } catch (e: Exception) {
            emptyList()
        }
        val databaseList = databaseRepository.getItems().map { it.databaseToData() }

        val larger = if (networkList.size > databaseList.size) networkList else databaseList

        val list = arrayListOf<TodoItemData>()

        for (item in larger) {
            val first = networkList.firstOrNull { it.id == item.id }
            val second = databaseList.firstOrNull { it.id == item.id }

            if ((first?.changedAt ?: -1) > (second?.changedAt ?: -1)) {
                if (first != null) list.add(first)
            } else {
                if (second != null) list.add(second)
            }
        }

        for (item in list) {
            updateElement(item.toDomain())

            databaseRepository.updateItem(
                item.id,
                item.name,
                item.isDone,
                item.changedAt
            )
        }
    }

    override suspend fun addElement(name: String) {
        val uuid = UUID.randomUUID().toString()

        try {
            val time = System.currentTimeMillis()


            val mock = TodoDto.createMock(
                id = uuid,
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

        } catch (e: Exception) {
            databaseRepository.addElement(uuid, name)
        }

    }

    override suspend fun getElement(id: String): TodoItem {
        val item = try {
            val response = todoItemsApi.getElement(id)
            revision = response.revision
            response.element.toDomain()
        } catch (e: Exception) {
            databaseRepository.getItem(id).toDomain()
        }



        return item
    }

    override suspend fun deleteElement(id: String) {
        try {
            val response = todoItemsApi.deleteElement(revision, id)
            revision = response.revision
        } catch (e: Exception) {
            databaseRepository.deleteItem(id)
        }
    }

    override suspend fun updateElement(todoItem: TodoItem) {
        val changedAt = System.currentTimeMillis()

        val element = todoItem.toData(changedAt)

        val request = AddElementRequest(element)

        try {
            val response = todoItemsApi.updateElement(
                revision,
                element.id,
                request
            )

            revision = response.revision
        } catch (e: Exception) {
            databaseRepository.updateItem(todoItem.id, todoItem.text, todoItem.isDone, changedAt)
        }
    }
}