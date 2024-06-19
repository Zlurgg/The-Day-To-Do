package uk.co.zlurgg.thedaytodo.domain.use_case

import uk.co.zlurgg.thedaytodo.domain.model.Todo
import uk.co.zlurgg.thedaytodo.domain.repository.TodoRepository

class GetTodo(
    private val repository: TodoRepository
) {

    suspend operator fun invoke(id: Int): Todo? {
        return repository.getTodoById(id)
    }
}