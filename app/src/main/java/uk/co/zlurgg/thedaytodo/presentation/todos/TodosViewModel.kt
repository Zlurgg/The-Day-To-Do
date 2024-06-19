package uk.co.zlurgg.thedaytodo.presentation.todos

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uk.co.zlurgg.thedaytodo.domain.model.Todo
import uk.co.zlurgg.thedaytodo.domain.use_case.TodoUseCases
import uk.co.zlurgg.thedaytodo.domain.utils.OrderType
import uk.co.zlurgg.thedaytodo.domain.utils.TodoOrder
import javax.inject.Inject


@HiltViewModel
class TodosViewModel @Inject constructor(
    private val todoUseCases: TodoUseCases
) : ViewModel() {

    private val _state = mutableStateOf(TodosState())
    val state: State<TodosState> = _state

    private var recentlyDeletedTodo: Todo? = null

    private var getTodosJob: Job? = null

    init {
        getTodos(TodoOrder.Date(OrderType.Descending))
    }

    fun onEvent(event: TodosEvent) {
        when (event) {
            is TodosEvent.Order -> {
                if (state.value.todoOrder::class == event.todoOrder::class &&
                    state.value.todoOrder.orderType == event.todoOrder.orderType
                ) {
                    return
                }
                getTodos(event.todoOrder)
            }
            is TodosEvent.DeleteTodo -> {
                viewModelScope.launch {
                    todoUseCases.deleteTodo(event.todo)
                    recentlyDeletedTodo = event.todo
                }
            }
            is TodosEvent.RestoreTodo -> {
                viewModelScope.launch {
                    todoUseCases.addTodo(recentlyDeletedTodo ?: return@launch)
                    recentlyDeletedTodo = null
                }
            }
            is TodosEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
        }
    }

    private fun getTodos(todoOrder: TodoOrder) {
        getTodosJob?.cancel()
        getTodosJob = todoUseCases.getTodos(todoOrder)
            .onEach { todos ->
                _state.value = state.value.copy(
                    todos = todos,
                    todoOrder = todoOrder
                )
            }
            .launchIn(viewModelScope)
    }
}