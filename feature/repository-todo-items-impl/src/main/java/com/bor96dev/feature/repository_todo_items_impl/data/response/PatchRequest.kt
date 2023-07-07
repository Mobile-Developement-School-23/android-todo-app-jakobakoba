package com.bor96dev.feature.repository_todo_items_impl.data.response

import com.bor96dev.feature.repository_todo_items_impl.data.dto.TodoDto
import com.squareup.moshi.Json

internal data class PatchRequest(
    @field:Json(name = "list")
    val list: List<TodoDto>
)
