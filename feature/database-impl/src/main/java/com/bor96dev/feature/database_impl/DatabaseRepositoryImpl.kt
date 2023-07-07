package com.bor96dev.feature.database_impl

import com.bor96dev.feature.database_api.DatabaseRepository
import com.bor96dev.feature.database_api.TodoItemEntity
import com.bor96dev.feature.database_impl.dao.TodoItemDao
import com.bor96dev.feature.database_impl.data.TodoItemData
import com.bor96dev.feature.database_impl.data.toApi
import javax.inject.Inject


internal class DatabaseRepositoryImpl @Inject constructor(
    private val todoItemDao: TodoItemDao,
    private val revisionPref: RevisionPref
) : DatabaseRepository {
    override suspend fun addElement(
        uuid: String,
        name: String,
    ) {
        val item = TodoItemData(uuid, name, false, System.currentTimeMillis())

        todoItemDao.insert(item)
    }

    override suspend fun getItems(): List<TodoItemEntity> {
        return todoItemDao.getList().map { it.toApi() }
    }

    override suspend fun getItem(id: String): TodoItemEntity {
        return todoItemDao.getItem(id).toApi()
    }

    override suspend fun fullDelete() {
        todoItemDao.fullDelete()
    }

    override suspend fun deleteItem(id: String) {
        todoItemDao.deleteItem(id)
    }

    override suspend fun updateItem(
        id: String,
        text: String,
        isDone: Boolean,
        changedAt: Long
    ) {
        todoItemDao.updateItem(id, text, isDone, changedAt)
    }

    override suspend fun getRevision(): Long {
        return revisionPref.getRevision()
    }

    override suspend fun setRevision(revision: Long) {
        revisionPref.updateRevision(revision)
    }

    override suspend fun incremeentRevision() {
        val revision =  revisionPref.getRevision() + 1
        revisionPref.updateRevision(revision)
    }
}
