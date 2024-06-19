package uk.co.zlurgg.thedaytodo.data.repository

import kotlinx.coroutines.flow.Flow
import uk.co.zlurgg.thedaytodo.data.data_source.TodoDao
import uk.co.zlurgg.thedaytodo.domain.model.Todo
import uk.co.zlurgg.thedaytodo.domain.repository.TodoRepository

class TodoRepositoryImpl(
    private val dao: TodoDao
) : TodoRepository {

    override suspend fun insertTodo(todo: Todo) {
        dao.insertTodo(todo)
    }

    override suspend fun deleteTodo(todo: Todo) {
        dao.deleteTodo(todo)
    }

    override suspend fun getTodoById(id: Int): Todo? {
        return dao.getTodoById(id)
    }

    override fun getTodos(): Flow<List<Todo>> {
        return dao.getTodos()
    }
}