package uk.co.zlurgg.thedaytodo.domain.use_case

data class TodoUseCases(
    val getTodos: GetTodos,
    val deleteTodo: DeleteTodo,
    val addTodo: AddTodo,
    val getTodo: GetTodo
)
