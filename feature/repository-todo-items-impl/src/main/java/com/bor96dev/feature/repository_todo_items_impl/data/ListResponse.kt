package com.bor96dev.feature.repository_todo_items_impl.data

import com.squareup.moshi.Json

internal data class ListResponse(
    @field:Json(name = "list")
    val list: List<TodoDto>,
    @field:Json(name = "status")
    val status: String,
    @field:Json(name = "revision")
    val revision: Long
)