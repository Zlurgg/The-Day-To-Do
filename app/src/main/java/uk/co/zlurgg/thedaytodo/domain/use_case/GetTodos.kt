package uk.co.zlurgg.thedaytodo.domain.use_case

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import uk.co.zlurgg.thedaytodo.domain.model.Todo
import uk.co.zlurgg.thedaytodo.domain.repository.TodoRepository
import uk.co.zlurgg.thedaytodo.domain.utils.OrderType
import uk.co.zlurgg.thedaytodo.domain.utils.TodoOrder

class GetTodos(
    private val repository: TodoRepository
) {

    operator fun invoke(
        todoOrder: TodoOrder = TodoOrder.Date(OrderType.Descending)
    ): Flow<List<Todo>> {
        return repository.getTodos().map { todos ->
            when(todoOrder.orderType) {
                is OrderType.Ascending -> {
                    when(todoOrder) {
                        is TodoOrder.Title -> todos.sortedBy { it.title.lowercase() }
                        is TodoOrder.Date -> todos.sortedBy { it.timestamp }
                    }
                }
                is OrderType.Descending -> {
                    when(todoOrder) {
                        is TodoOrder.Title -> todos.sortedByDescending { it.title.lowercase() }
                        is TodoOrder.Date -> todos.sortedByDescending { it.timestamp }
                    }
                }
            }
        }
    }
}