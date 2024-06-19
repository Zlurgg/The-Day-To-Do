package uk.co.zlurgg.thedaytodo.presentation.add_edit_todo

data class TodoTextFieldState(
    val text: String = "",
    val hint: String = "",
    val isHintVisible: Boolean = true
)