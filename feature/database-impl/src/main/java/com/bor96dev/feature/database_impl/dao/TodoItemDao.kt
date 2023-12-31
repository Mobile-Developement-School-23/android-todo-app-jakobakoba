package com.bor96dev.feature.database_impl.dao

import android.database.sqlite.SQLiteException
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

    @Query("SELECT * FROM todoitemdata WHERE uid = :id")
    @Throws(SQLiteException::class)
    fun getItem(id: String): TodoItemData

    @Query("DELETE FROM todoitemdata WHERE uid = :id")
    fun deleteItem(id:String)

    @Query("DELETE FROM todoitemdata")
    fun fullDelete()

    @Query(
        "UPDATE todoitemdata " +
                "SET name = :name, is_done = :isDone, priority = :priority, changed_at = :changedAt " +
                "WHERE uid = :id"
    )
    fun updateItem(id: String, name: String, priority: String, isDone: Boolean, changedAt: Long)
}