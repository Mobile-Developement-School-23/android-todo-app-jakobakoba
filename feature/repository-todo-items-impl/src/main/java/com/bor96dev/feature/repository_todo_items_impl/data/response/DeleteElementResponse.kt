package com.bor96dev.feature.repository_todo_items_impl.data.response

import com.squareup.moshi.Json

internal data class DeleteElementResponse(
    @field:Json(name = "revision")
    val revision: Long
)