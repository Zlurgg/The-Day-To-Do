package uk.co.zlurgg.thedaytodo.presentation.add_edit_todo

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import uk.co.zlurgg.thedaytodo.domain.model.InvalidTodoException
import uk.co.zlurgg.thedaytodo.domain.model.Todo
import uk.co.zlurgg.thedaytodo.domain.use_case.TodoUseCases
import javax.inject.Inject

@HiltViewModel
class AddEditTodoViewModel @Inject constructor(
    private val todoUseCases: TodoUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _todoTitle = mutableStateOf(TodoTextFieldState(
        hint = "Enter title..."
    ))
    val todoTitle: State<TodoTextFieldState> = _todoTitle

    private val _todoContent = mutableStateOf(TodoTextFieldState(
        hint = "Enter some content"
    ))
    val todoContent: State<TodoTextFieldState> = _todoContent

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentTodoId: Int? = null

    init {
        savedStateHandle.get<Int>("todoId")?.let { todoId ->
            if(todoId != -1) {
                viewModelScope.launch {
                    todoUseCases.getTodo(todoId)?.also { todo ->
                        currentTodoId = todo.id
                        _todoTitle.value = todoTitle.value.copy(
                            text = todo.title,
                            isHintVisible = false
                        )
                        _todoContent.value = _todoContent.value.copy(
                            text = todo.content,
                            isHintVisible = false
                        )
                    }
                }
            }
        }
    }

    fun onEvent(event: AddEditTodoEvent) {
        when(event) {
            is AddEditTodoEvent.EnteredTitle -> {
                _todoTitle.value = todoTitle.value.copy(
                    text = event.value
                )
            }
            is AddEditTodoEvent.ChangeTitleFocus -> {
                _todoTitle.value = todoTitle.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            todoTitle.value.text.isBlank()
                )
            }
            is AddEditTodoEvent.EnteredContent -> {
                _todoContent.value = _todoContent.value.copy(
                    text = event.value
                )
            }
            is AddEditTodoEvent.ChangeContentFocus -> {
                _todoContent.value = _todoContent.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            _todoContent.value.text.isBlank()
                )
            }
            is AddEditTodoEvent.SaveTodo -> {
                viewModelScope.launch {
                    try {
                        todoUseCases.addTodo(
                            Todo(
                                title = todoTitle.value.text,
                                content = todoContent.value.text,
                                timestamp = System.currentTimeMillis(),
                                id = currentTodoId
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveTodo)
                    } catch(e: InvalidTodoException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = e.message ?: "Couldn't save Todo"
                            )
                        )
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String): UiEvent()
        object SaveTodo: UiEvent()
    }
}