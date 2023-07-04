package com.bor96dev.feature.database_impl.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bor96dev.feature.database_impl.dao.TodoItemDao

@Database(entities = [TodoItemEntity::class], version = 1)
internal abstract class TodoItemDatabase : RoomDatabase() {

    abstract fun todoItemDao(): TodoItemDao
}