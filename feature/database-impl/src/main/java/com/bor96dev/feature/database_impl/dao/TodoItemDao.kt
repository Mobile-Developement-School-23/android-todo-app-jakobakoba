package com.bor96dev.feature.database_impl.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bor96dev.feature.database_impl.data.TodoItemData

@Dao
internal interface TodoItemDao {

    @Query("SELECT * FROM todoitemdata")
    suspend fun getList(): List<TodoItemData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: TodoItemData)

}