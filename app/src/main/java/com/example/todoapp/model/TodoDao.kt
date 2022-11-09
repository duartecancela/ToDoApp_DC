package com.example.todoapp.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
abstract class TodoDao {

    @Query("select * from todo")
    abstract fun getAll() : List<Todo>

    @Query("select * from todo where id = :id")
    abstract fun getTodo(id: Long) : Todo

    @Insert
    abstract fun insert(todo: Todo) : Long

    @Query("delete from todo where id = :id")
    abstract fun delete(id: Int): Int
}