package com.bor96dev.yandextodoapp.core.feature.todo_items_impl

import com.bor96dev.feature.repository_todo_items_api.TodoItemsRepository
import com.bor96dev.yandextodoapp.core.feature.todo_items_api.domain.TodoItem
import com.bor96dev.yandextodoapp.core.feature.todo_items_api.domain.TodoItemPriority
import com.bor96dev.yandextodoapp.core.feature.todo_items_api.domain.TodoItemsInteractor
import javax.inject.Inject

internal class TodoItemsInteractorImpl @Inject constructor(
    private val repository: TodoItemsRepository
) : TodoItemsInteractor {

    override suspend fun addItem(
        text: String,
        priority: TodoItemPriority
    ) {
        repository.addElement(text, priority)
    }

    override suspend fun makeIsDone(id: String) {
        val element = repository.getElement(id)
        repository.updateElement(element.copy(isDone = !element.isDone))
    }

    override suspend fun getItem(id: String): TodoItem {
        return repository.getElement(id)
    }

    override suspend fun removeItem(id: String) {
        repository.deleteElement(id)
    }

    override suspend fun getItems(): List<TodoItem> {
        return repository.getList()
    }

    override suspend fun updateItem(item: TodoItem) {
        repository.updateElement(item)
    }
}