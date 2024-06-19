package uk.co.zlurgg.thedaytodo.domain.use_case

import uk.co.zlurgg.thedaytodo.domain.model.InvalidTodoException
import uk.co.zlurgg.thedaytodo.domain.model.Todo
import uk.co.zlurgg.thedaytodo.domain.repository.TodoRepository

class AddTodo(
    private val repository: TodoRepository
) {
    @Throws(InvalidTodoException::class)
    suspend operator fun invoke(todo: Todo) {
        if(todo.title.isBlank()) {
            throw InvalidTodoException("The title of the todo can't be empty.")
        }
        if(todo.content.isBlank()) {
            throw InvalidTodoException("The content of the todo can't be empty.")
        }
        repository.insertTodo(todo)
    }
}