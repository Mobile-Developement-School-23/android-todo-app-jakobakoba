package com.bor96dev.feature.create_impl.presentation.model

import com.bor96dev.yandextodoapp.core.feature.create_impl.R
import com.bor96dev.yandextodoapp.core.feature.todo_items_api.domain.TodoItemPriority

fun getPriorityList() = arrayOf(
    PriorityUi(
        TodoItemPriority.LOW,
        R.string.priority_low
    ),
    PriorityUi(
        TodoItemPriority.NORMAL,
        R.string.priority_normal
    ),
    PriorityUi(
        TodoItemPriority.URGENT,
        R.string.priority_urgent
    )
)