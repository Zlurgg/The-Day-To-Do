package uk.co.zlurgg.thedaytodo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import uk.co.zlurgg.thedaytodo.presentation.add_edit_todo.AddEditTodoScreen
import uk.co.zlurgg.thedaytodo.presentation.todos.TodosScreen
import uk.co.zlurgg.thedaytodo.ui.theme.TheDayToDoTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TheDayToDoTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "todos_screen"
                ) {
                    composable("todos_screen") { TodosScreen(navController) }
                    composable("add_edit_todo_screen" + "?todoId={todoId}",
                        arguments = listOf(
                            navArgument(
                                name = "todoId"
                            ) {
                                type = NavType.IntType
                                defaultValue = -1
                            }
                        )
                    ) {
                        AddEditTodoScreen(navController)
                    }
                }
            }
        }
    }
}
