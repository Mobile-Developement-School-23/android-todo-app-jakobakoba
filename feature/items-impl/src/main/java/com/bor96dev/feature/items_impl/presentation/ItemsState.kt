package com.bor96dev.feature.items_impl.presentation

import com.bor96dev.yandextodoapp.core.feature.items_impl.R
import com.bor96dev.yandextodoapp.core.feature.todo_items_api.domain.TodoItem

data class ItemsState(
    val isDoneVisible: Boolean = false,
    val doneDrawable: Int = R.drawable.eye_all,
    val doneText: String = "0 ",
    val items: List<TodoItem> = emptyList()
)