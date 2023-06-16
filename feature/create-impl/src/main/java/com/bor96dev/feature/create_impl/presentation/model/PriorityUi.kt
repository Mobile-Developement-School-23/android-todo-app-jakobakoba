package com.bor96dev.feature.create_impl.presentation.model

import com.bor96dev.yandextodoapp.core.feature.todo_items_api.domain.TodoItemPriority

data class PriorityUi(
    val id: TodoItemPriority,
    val textId: Int,
)