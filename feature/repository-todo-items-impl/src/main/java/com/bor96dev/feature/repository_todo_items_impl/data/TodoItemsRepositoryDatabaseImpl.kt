import com.bor96dev.feature.database_api.DatabaseApi
import com.bor96dev.feature.database_api.DatabaseRepository
import com.bor96dev.feature.repository_todo_items_api.TodoItemsRepository
import com.bor96dev.yandextodoapp.core.feature.todo_items_api.domain.TodoItem
import javax.inject.Inject

internal class TodoItemsRepositoryDatabaseImpl @Inject constructor(
    private val databaseRepository: DatabaseRepository,
) {

    suspend fun addElement(){
        databaseRepository.addElement("asd")
    }
}