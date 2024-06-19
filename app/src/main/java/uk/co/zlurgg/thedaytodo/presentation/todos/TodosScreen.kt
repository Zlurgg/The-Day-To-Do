package uk.co.zlurgg.thedaytodo.presentation.todos

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import uk.co.zlurgg.thedaytodo.presentation.todos.components.TodoItem

@Composable
fun TodosScreen(
    navController: NavController,
    viewModel: TodosViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val scope = rememberCoroutineScope()
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate("add_edit_todo_screen")
                },
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add todo")
            }
        },
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(state.todos) { todo ->
                TodoItem(
                    todo = todo,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate("add_edit_todo_screen" + "?todoId=${todo.id}")
                        },
                    onDeleteClick = {
                        viewModel.onEvent(TodosEvent.DeleteTodo(todo))
                    }
                )
            }
        }
    }
}