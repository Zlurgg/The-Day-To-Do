package uk.co.zlurgg.thedaytodo.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uk.co.zlurgg.thedaytodo.data.data_source.TodoDao
import uk.co.zlurgg.thedaytodo.data.data_source.TodoDatabase
import uk.co.zlurgg.thedaytodo.data.repository.TodoRepositoryImpl
import uk.co.zlurgg.thedaytodo.domain.repository.TodoRepository
import uk.co.zlurgg.thedaytodo.domain.use_case.AddTodo
import uk.co.zlurgg.thedaytodo.domain.use_case.DeleteTodo
import uk.co.zlurgg.thedaytodo.domain.use_case.GetTodo
import uk.co.zlurgg.thedaytodo.domain.use_case.GetTodos
import uk.co.zlurgg.thedaytodo.domain.use_case.TodoUseCases
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
            TodoDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideTodoRepository(db: TodoDatabase): TodoRepository {
        return TodoRepositoryImpl(db.todoDao())
    }

    @Provides
    @Singleton
    fun provideTodoUseCases(repository: TodoRepository): TodoUseCases {
        return TodoUseCases(
            getTodos = GetTodos(repository),
            deleteTodo = DeleteTodo(repository),
            addTodo = AddTodo(repository),
            getTodo = GetTodo(repository = repository)
        )
    }
}