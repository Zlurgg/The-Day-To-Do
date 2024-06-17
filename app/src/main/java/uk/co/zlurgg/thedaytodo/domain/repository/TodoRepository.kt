package uk.co.zlurgg.thedaytodo.domain.repository

import kotlinx.coroutines.flow.Flow
import uk.co.zlurgg.thedaytodo.domain.model.Todo

interface TodoRepository {

    suspend fun insertTodo(todo: Todo)

    suspend fun deleteTodo(todo: Todo)

    suspend fun getTodoById(id: Int): Todo?

    fun getTodos(): Flow<List<Todo>>
}