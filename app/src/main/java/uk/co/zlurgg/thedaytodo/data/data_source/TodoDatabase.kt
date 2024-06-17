package uk.co.zlurgg.thedaytodo.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import uk.co.zlurgg.thedaytodo.domain.model.Todo

@Database(
    entities = [Todo::class],
    version = 1,
    exportSchema = false
)
abstract class TodoDatabase: RoomDatabase() {
    abstract fun todoDao(): TodoDao

    companion object {
        const val DATABASE_NAME = "the_day_to_do_database"
    }

}