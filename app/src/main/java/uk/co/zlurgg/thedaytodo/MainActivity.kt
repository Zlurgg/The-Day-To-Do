package uk.co.zlurgg.thedaytodo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import uk.co.zlurgg.thedaytodo.presentation.todo_list.TodoListScreen
import uk.co.zlurgg.thedaytodo.ui.theme.TheDayToDoTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TheDayToDoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TodoListScreen(Modifier.padding(innerPadding))
                }
            }
        }
    }
}
