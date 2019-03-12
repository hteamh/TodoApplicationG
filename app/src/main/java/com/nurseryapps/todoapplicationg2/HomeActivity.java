package com.nurseryapps.todoapplicationg2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;

import com.nurseryapps.todoapplicationg2.Base.BaseActivity;
import com.nurseryapps.todoapplicationg2.BataBase.Model.Todo;
import com.nurseryapps.todoapplicationg2.BataBase.MyDataBase;

import java.util.List;

public class HomeActivity extends BaseActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    TodoRecyclerAdapter adapter;
    SwipeRefreshLayout swipeRefreshLayout;
    Todo swipedItem =null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = findViewById(R.id.recycler_view);
        swipeRefreshLayout = findViewById(R.id.swipLayout);
        setAdapter();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getAllTodosFromDB();
            }
        });
        ItemTouchHelper itemTouchHelper=new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.DOWN,ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                if(direction==ItemTouchHelper.RIGHT){
                    Log.e("direction","right");
                }else {
                    Log.e("direction","left");
                }
                int pos=viewHolder.getAdapterPosition();
                Todo item = adapter.getSwipedItem(pos);
                swipedItem = item;
                deleteTodo(item);

            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
        /*        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
      */
                Intent i = new Intent(HomeActivity.this,AddTodoActivity.class);
                startActivity(i);
            }
        });
    }

    public void deleteTodo(Todo item){
        MyDataBase.getInstance(activity)
                .todoDao()
                .RemoveTodo(item);
        Snackbar.make(findViewById(R.id.parent),R.string.item_deleted,8000)
                .setAction(R.string.undo, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addTodo();
                    }
                }).show();
        getAllTodosFromDB();
    }

    public void addTodo(){
        MyDataBase.getInstance(activity)
                .todoDao()
                .AddTodo(swipedItem);
        getAllTodosFromDB();
        swipedItem=null;
    }
    public void setAdapter(){
        layoutManager = new LinearLayoutManager(activity);
        adapter = new TodoRecyclerAdapter(null);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        adapter.setOnItemClickListener(new TodoRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos, Todo item) {
                AddTodoActivity.todoItem =item;
                Intent i = new Intent(HomeActivity.this,AddTodoActivity.class);
                startActivity(i);

            }
        });
    }

    public void getAllTodosFromDB(){
        List<Todo> items = MyDataBase.getInstance(activity)
                .todoDao()
                .getAllTodo();
        adapter.ChangeData(items);
        swipeRefreshLayout.setRefreshing(false);

    }
    @Override
    protected void onStart() {
        super.onStart();
        getAllTodosFromDB();
      /*  adapter=new TodoRecyclerAdapter(items);
        recyclerView.setAdapter(adapter);*/
    }
}
