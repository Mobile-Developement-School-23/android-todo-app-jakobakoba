package com.bor96dev.feature.create_impl.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bor96dev.yandextodoapp.core.feature.todo_items_api.domain.TodoItem
import com.bor96dev.yandextodoapp.core.feature.todo_items_api.domain.TodoItemPriority
import com.bor96dev.yandextodoapp.core.feature.todo_items_api.domain.TodoItemsInteractor
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CreateViewModel @Inject constructor(
    private val router: Router,
    private val todoItemsInteractor: TodoItemsInteractor,
) : ViewModel() {

    private val _screenState = MutableSharedFlow<TodoItem>()
    val state: SharedFlow<TodoItem> = _screenState

    fun recreateScreen(id: String) {
        viewModelScope.launch {
            if (id.isNotEmpty()) {
                val item = todoItemsInteractor.getItem(id)
                _screenState.emit(item)
            }
        }
    }
    fun removeItemButtonClicked(id: String) {
        viewModelScope.launch {
            todoItemsInteractor.removeItem(id)
        }
        router.exit()
    }
    fun onExitButtonClicked() {
        router.exit()
    }
    fun onSaveButtonClicked(
        text: String,
        priority: TodoItemPriority
    ) {
        viewModelScope.launch {
            todoItemsInteractor.addItem(text, priority)
        }
        router.exit()
    }
}