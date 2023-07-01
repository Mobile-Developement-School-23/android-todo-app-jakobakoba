package com.bor96dev.feature.repository_todo_items_impl.data.response

import com.bor96dev.feature.repository_todo_items_impl.data.dto.TodoDto
import com.squareup.moshi.Json

internal data class AddElementRequest(
    @field:Json(name = "element")
    val element: TodoDto,
)