package com.bor96dev.feature.repository_todo_items_impl.data.response

import com.bor96dev.feature.repository_todo_items_impl.data.dto.TodoDto
import com.squareup.moshi.Json

internal data class GetElementResponse(
    @field:Json(name = "element")
    val element: TodoDto,
    @field:Json(name = "status")
    val status: String,
    @field:Json(name = "revision")
    val revision: Long
)