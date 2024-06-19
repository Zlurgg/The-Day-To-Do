package uk.co.zlurgg.thedaytodo.presentation.add_edit_todo

import androidx.compose.ui.focus.FocusState

sealed class AddEditTodoEvent{
    data class EnteredTitle(val value: String): AddEditTodoEvent()
    data class ChangeTitleFocus(val focusState: FocusState): AddEditTodoEvent()
    data class EnteredContent(val value: String): AddEditTodoEvent()
    data class ChangeContentFocus(val focusState: FocusState): AddEditTodoEvent()
    data object SaveTodo: AddEditTodoEvent()
}