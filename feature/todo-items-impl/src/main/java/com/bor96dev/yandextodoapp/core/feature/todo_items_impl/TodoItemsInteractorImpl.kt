package com.bor96dev.yandextodoapp.core.feature.todo_items_impl

import com.bor96dev.feature.repository_todo_items_api.TodoItemsRepository
import com.bor96dev.yandextodoapp.core.feature.todo_items_api.domain.TodoItem
import com.bor96dev.yandextodoapp.core.feature.todo_items_api.domain.TodoItemPriority
import com.bor96dev.yandextodoapp.core.feature.todo_items_api.domain.TodoItemsInteractor
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

internal class TodoItemsInteractorImpl @Inject constructor(
    private val repository: TodoItemsRepository
) : TodoItemsInteractor {

    private val todoItems: MutableStateFlow<List<TodoItem>> = MutableStateFlow(
        getMockItems()
    )

    override suspend fun addItem(
        text: String,
        priority: TodoItemPriority
    ) {
        val list = todoItems.value.toMutableList()
        list.add(
            TodoItem(
                System.currentTimeMillis().toString(),
                text,
                priority,
                false
            )
        )
        todoItems.emit(list)
    }

    override suspend fun makeIsDone(id: String, isDone: Boolean) {
        val list = todoItems.value.toMutableList()
        val itemPosition = list.indexOfFirst { it.id == id }
        list[itemPosition] = list[itemPosition].copy(isDone = isDone)
        todoItems.emit(list)
    }

    override fun getItem(id: String): TodoItem {
        return todoItems.value.find { it.id == id }!!
    }

    override suspend fun removeItem(id: String) {
        val array = todoItems.value.filter { it.id != id }
        todoItems.emit(array)
    }

    override suspend fun getItems(): List<TodoItem> {
        return repository.getList()
    }

    private fun getMockItems(): List<TodoItem> {
        return listOf(
            TodoItem("1", "Сделать проект", TodoItemPriority.LOW, true),
            TodoItem("2", "Сходить за хлебом", TodoItemPriority.NORMAL, false),
            TodoItem("3", "Покормить собаку", TodoItemPriority.URGENT, false),
            TodoItem("4", "Посмотреть фильм, купить чипсы", TodoItemPriority.LOW, false),
            TodoItem(
                "5",
                "Сходить в парикмахерскую, выбрать прическу, почистить кроссовки",
                TodoItemPriority.NORMAL,
                false
            ),
            TodoItem("6", "Я смотрю далеко вниз налево направо", TodoItemPriority.URGENT, false),
            TodoItem("7", "Кот", TodoItemPriority.LOW, false),
            TodoItem("8", "К", TodoItemPriority.NORMAL, false),
            TodoItem("9", "Прогуляться по парку", TodoItemPriority.URGENT, false),
            TodoItem(
                "10",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce consectetur consequat nunc, ac pulvinar mauris tincidunt non. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Sed feugiat lectus tortor, vitae rhoncus libero tempor sit amet. Duis ultricies magna vitae erat feugiat, at lacinia urna suscipit. Phasellus id justo eget orci suscipit tempor. Vivamus auctor orci sit amet gravida sollicitudin. Nulla dapibus sapien a mauris facilisis, eget congue neque dignissim. Quisque elementum elit ac tellus ullamcorper lacinia. Curabitur aliquam dapibus vulputate. Donec vitae tellus auctor, pulvinar turpis eget, eleifend sem. In id nulla et purus volutpat tempor sed ut sem. Mauris nec justo tristique, efficitur elit sit amet, vestibulum metus. Aliquam sit amet eleifend velit, at fringilla leo.\n",
                TodoItemPriority.LOW,
                false
            ),
            TodoItem("11", "Мандарин", TodoItemPriority.NORMAL, false),
            TodoItem("12", "Апельсин", TodoItemPriority.URGENT, false),
            TodoItem("13", "Сок", TodoItemPriority.LOW, false),
            TodoItem("14", "Морковь", TodoItemPriority.NORMAL, false),
            TodoItem("15", "Арбуз", TodoItemPriority.URGENT, false),
        )
    }
}