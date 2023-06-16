package com.bor96dev.feature.items_impl.presentation

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bor96dev.feature.create_api.CreateApi
import com.bor96dev.yandextodoapp.core.feature.todo_items_api.domain.TodoItem
import com.bor96dev.yandextodoapp.core.feature.todo_items_api.domain.TodoItemsInteractor
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class ItemsViewModel @Inject constructor(
    private val todoItemsInteractor: TodoItemsInteractor,
    private val router: Router,
    private val createApi: CreateApi
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
        router.navigateTo(object : FragmentScreen {
            override fun createFragment(factory: FragmentFactory): Fragment =
                createApi.getFragment()
        })

//        viewModelScope.launch {
//            val id = Random.nextInt(1000).toString()
//            todoItemsInteractor.addItem(TodoItem(id, "name : $id"))
//        }
    }
}