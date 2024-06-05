package uk.co.zlurgg.thedaytodo.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_items")
data class Todo(
    val title: String,
    val description: String?,
    val date: String,
    val isComplete: Boolean,
    @PrimaryKey val id: Int? = null
)
