package com.bor96dev.feature.database_impl.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
internal data class TodoItemEntity(
    @PrimaryKey val uid: String,
    @ColumnInfo(name = "name") val name: String,
)