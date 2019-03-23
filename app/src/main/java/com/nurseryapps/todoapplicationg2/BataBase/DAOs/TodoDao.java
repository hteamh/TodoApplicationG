package com.nurseryapps.todoapplicationg2.BataBase.DAOs;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.nurseryapps.todoapplicationg2.BataBase.Model.Todo;

import java.util.List;


@Dao
public interface TodoDao {
    @Insert
    public void AddTodo(Todo item);

    @Delete
    public void RemoveTodo(Todo item);

    @Update
    public void updateTodo(Todo item);

    @Query("select * from todo;")
    List<Todo> getAllTodo();

    @Query("select * from todo where id=:id;")
    Todo searchById(int id);
    @Query("select * from todo where title like :subText;")
    List<Todo> searchById(String subText);

}
