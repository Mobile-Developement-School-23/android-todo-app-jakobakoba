package com.bor96dev.feature.repository_todo_items_impl.data

import android.util.Log
import com.bor96dev.feature.database_api.DatabaseRepository
import com.bor96dev.feature.repository_todo_items_api.TodoItemsRepository
import com.bor96dev.feature.repository_todo_items_impl.InternetChecker
import com.bor96dev.feature.repository_todo_items_impl.data.mapper.toData
import com.bor96dev.feature.repository_todo_items_impl.data.mapper.toDomain
import com.bor96dev.feature.repository_todo_items_impl.data.response.PatchRequest
import com.bor96dev.yandextodoapp.core.feature.todo_items_api.domain.TodoItem
import com.bor96dev.yandextodoapp.core.feature.todo_items_api.domain.TodoItemPriority
import com.bor96dev.yandextodoapp.core.feature.todo_items_api.domain.toData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject


internal class TodoItemsRepositoryImpl @Inject constructor(
    private val todoItemsApi: TodoItemsApi,
    private val databaseRepository: DatabaseRepository,
    private val internetChecker: InternetChecker
) : TodoItemsRepository {

    private var networkRevision = 0L

    override suspend fun getList(): List<TodoItem> {
        Log.d("GTA5", "BEFORE SYNC databaseRevision : ${databaseRepository.getRevision()}")
        syncData()
        val list = databaseRepository.getItems().map { it.toDomain() }

        Log.d(
            "GTA5", "ACTUAL : ${list.map { it.text }.convertToText()}\n" +
                    "networkRevision : $networkRevision\n" +
                    "databaseRevision : ${databaseRepository.getRevision()}"
        )

        return list
    }

    private suspend fun syncData() {
        try {
            if (internetChecker.isInternetAvailable()) {
                val response = todoItemsApi.getList()
                networkRevision = response.revision
                val networkList = response.list.map { it.toDomain() }
                val databaseList = databaseRepository.getItems().map { it.toDomain() }
                val databaseRevision = databaseRepository.getRevision()

                Log.d(
                    "GTA5", "networkRevision : ${networkRevision}\n" +
                            "network : ${networkList.map { it.text }.convertToText()}\n" +
                            "databaseRevision : ${databaseRevision}\n" +
                            "database : ${databaseList.map { it.text }.convertToText()}"
                )

                if (databaseRevision >= networkRevision) {
                    val list = databaseList.map { it.toData() }

                    val patchResponse =
                        todoItemsApi.patch(networkRevision, PatchRequest(list))
                    networkRevision = patchResponse.revision
                } else {
                    databaseRepository.fullDelete()
                    for (item in networkList) {
                        databaseRepository.addElement(
                            item.id,
                            item.priority.toData(),
                            item.text
                        )
                    }
                }
                databaseRepository.setRevision(networkRevision)

            }
        } catch (e: Exception) {
            Log.e("GTA5", "[TodoItemsRepositoryImpl] :${e.message}")
        }
    }

    private fun List<String>.convertToText(): String {
        var text = ""

        for (item in this) {
            text += "$item\n"
        }

        return text
    }

    override suspend fun addElement(
        name: String,
        priority: TodoItemPriority,
    ) {
        val uuid = UUID.randomUUID().toString()
        databaseRepository.addElement(uuid, priority.toData(), name)
        databaseRepository.incremeentRevision()
    }

    override suspend fun getElement(id: String): TodoItem {
        val item = databaseRepository.getItem(id).toDomain()
        databaseRepository.incremeentRevision()
        return item
    }

    override suspend fun deleteElement(id: String) {
        databaseRepository.deleteItem(id)
        databaseRepository.incremeentRevision()
    }

    override suspend fun updateElement(todoItem: TodoItem) {
        databaseRepository.updateItem(
            todoItem.id,
            todoItem.text,
            todoItem.priority.toData(),
            todoItem.isDone,
            System.currentTimeMillis()
        )
        databaseRepository.incremeentRevision()
    }
}