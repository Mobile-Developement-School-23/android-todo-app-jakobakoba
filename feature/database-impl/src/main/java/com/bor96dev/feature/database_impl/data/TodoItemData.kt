package com.bor96dev.feature.database_impl.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bor96dev.feature.database_api.TodoItemEntity

@Entity
internal data class TodoItemData(
    @PrimaryKey val uid: String,
    @ColumnInfo(name = "name") val name: String,
)

internal fun TodoItemData.toApi(): TodoItemEntity  {
    return TodoItemEntity(
        uid, name
    )
}