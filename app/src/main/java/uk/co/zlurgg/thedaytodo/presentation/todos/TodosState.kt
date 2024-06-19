package uk.co.zlurgg.thedaytodo.presentation.todos

import uk.co.zlurgg.thedaytodo.domain.model.Todo
import uk.co.zlurgg.thedaytodo.domain.utils.OrderType
import uk.co.zlurgg.thedaytodo.domain.utils.TodoOrder

data class TodosState(
    val todos: List<Todo> = emptyList(),
    val todoOrder: TodoOrder = TodoOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)