package com.bor96dev.feature.database_impl.dao

import androidx.room.Dao
import androidx.room.Query
import com.bor96dev.feature.database_impl.data.TodoItemEntity

@Dao
internal interface TodoItemDao {

    @Query("SELECT * FROM todoitementity")
    suspend fun getList(): List<TodoItemEntity>

}