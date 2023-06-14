package com.bor96dev.feature.items_impl.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bor96dev.core.di.navigation.RootNavigation
import com.bor96dev.yandextodoapp.core.feature.todo_items_api.domain.TodoItem
import com.bor96dev.yandextodoapp.core.feature.todo_items_api.domain.TodoItemsInteractor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

internal class ItemsViewModel @Inject constructor(
    private val rootNavigation: RootNavigation,
    private val todoItemsInteractor: TodoItemsInteractor
) : ViewModel() {


    private val _state = MutableStateFlow(emptyList<TodoItem>())
    val state: StateFlow<List<TodoItem>> = _state

    init {
        viewModelScope.launch {
            todoItemsInteractor.getItems().collectLatest {
                Log.d("GTA5", "$it")
                _state.emit(it)
            }
        }


    }

    fun onAddButtonClicked() {
        viewModelScope.launch {
            val id = Random.nextInt(1000).toString()
            todoItemsInteractor.addItem(TodoItem(id, "name : $id"))
        }
    }
}