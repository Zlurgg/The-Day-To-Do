package uk.co.zlurgg.thedaytodo.presentation.todos

import uk.co.zlurgg.thedaytodo.domain.model.Todo
import uk.co.zlurgg.thedaytodo.domain.utils.TodoOrder

sealed class TodosEvent {
    data class Order(val todoOrder: TodoOrder): TodosEvent()
    data class DeleteTodo(val todo: Todo): TodosEvent()
    data object RestoreTodo: TodosEvent()
    data object ToggleOrderSection: TodosEvent()
}