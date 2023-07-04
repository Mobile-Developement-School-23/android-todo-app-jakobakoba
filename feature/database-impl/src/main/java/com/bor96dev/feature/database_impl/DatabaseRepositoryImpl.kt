package com.bor96dev.feature.database_impl

import com.bor96dev.feature.database_api.DatabaseRepository
import javax.inject.Inject


internal class DatabaseRepositoryImpl @Inject constructor(
//    private val todoItemDao: TodoItemDao
) : DatabaseRepository {
    override suspend fun addElement(name: String) {

    }
}
