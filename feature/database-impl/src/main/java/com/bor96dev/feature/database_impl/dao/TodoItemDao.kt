package com.bor96dev.feature.database_impl.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.bor96dev.yandextodoapp.core.feature.todo_items_api.domain.TodoItem

@Dao
interface TodoItemDao {

    suspend fun getList(): List<TodoItem>

    @Insert(onConflict = REPLACE)
    suspend fun addElement(name: String)

    @Query("SELECT * FROM itemsTable WHERE `id` = :id")
    suspend fun getElement(id: String): TodoItem

    suspend fun deleteElement(id: String)

    suspend fun updateElement(todoItem: TodoItem)
}