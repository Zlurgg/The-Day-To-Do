package uk.co.zlurgg.thedaytodo.data

import kotlinx.coroutines.flow.Flow

class TodoRepositoryImpl(
    private val dao: TodoDao
): TodoRepository {

    override suspend fun insertTodo(todo: Todo) {
        dao.insert(todo)
    }

    override suspend fun deleteTodo(todo: Todo) {
        dao.delete(todo)
    }

    override suspend fun getTodoById(id: Int): Todo? {
        return dao.getByID(id)
    }

    override fun getTodos(): Flow<List<Todo>> {
        return dao.getAll()
    }
}