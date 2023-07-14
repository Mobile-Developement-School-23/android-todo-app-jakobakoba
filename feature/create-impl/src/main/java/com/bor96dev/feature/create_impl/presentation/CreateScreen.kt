package com.bor96dev.feature.create_impl.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.bor96dev.yandextodoapp.core.feature.todo_items_api.domain.TodoItem

@Composable
fun CreateScreen(
    state: TodoItem
) {
    Column() {
        Text(text = "Hello")
        
    }
}