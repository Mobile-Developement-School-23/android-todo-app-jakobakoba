package com.bor96dev.feature.repository_todo_items_impl.data

import com.squareup.moshi.Json

internal data class TodoDto (
    @field:Json(name = "id")
    val id: String,
    @field: Json(name = "done")
    val done: Boolean,
    @field: Json(name = "text")
    val text: String,
    @field: Json(name = "created_at")
    val created_at: Long,
    @field: Json(name = "last_updated_by")
    val last_updated_by: String,
    @field: Json(name = "importance")
    val importance: String,
    @field: Json(name = "color")
    val color: String,
    @field: Json(name = "changed_at")
    val changed_at: Long
)