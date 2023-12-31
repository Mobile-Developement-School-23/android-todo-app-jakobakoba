package com.bor96dev.feature.database_impl.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bor96dev.feature.database_api.TodoItemEntity

@Entity
internal data class TodoItemData(
    @PrimaryKey val uid: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "priority") val priority: String,
    @ColumnInfo(name = "is_done") val isDone: Boolean,
    @ColumnInfo(name = "changed_at") val changedAt: Long
)

internal fun TodoItemData.toApi(): TodoItemEntity {
    return TodoItemEntity(
        uid, name, isDone, priority, System.currentTimeMillis()
    )
}

