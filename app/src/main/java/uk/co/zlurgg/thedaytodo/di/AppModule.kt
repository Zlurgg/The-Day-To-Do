package uk.co.zlurgg.thedaytodo.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uk.co.zlurgg.thedaytodo.data.TodoDao
import uk.co.zlurgg.thedaytodo.data.TodoDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): TodoDatabase {
        return Room.databaseBuilder(
            app,
            TodoDatabase::class.java,
            "todo_database"
        ).build()
    }

    @Provides
    fun provideToDoDao(database: TodoDatabase): TodoDao {
        return database.todoDao()
    }
}