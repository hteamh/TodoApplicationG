package com.nurseryapps.todoapplicationg2.BataBase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.nurseryapps.todoapplicationg2.BataBase.DAOs.TodoDao;
import com.nurseryapps.todoapplicationg2.BataBase.Model.Todo;

/**
 * Created by Mohamed Nabil Mohamed (Nobel) on 1/28/2019.
 * byte code SA
 * m.nabil.fci2015@gmail.com
 */
@Database(entities = {Todo.class}, version = 1, exportSchema = false)
public abstract class MyDataBase extends RoomDatabase {

    public abstract TodoDao todoDao();

    private static MyDataBase myDataBase;
    public static MyDataBase getInstance(Context context){
        if(myDataBase==null){//Create new object
            myDataBase =
                    Room.databaseBuilder(context.getApplicationContext(),
                            MyDataBase.class, "Todo-DataBase")
                            .allowMainThreadQueries()
                            .build();
        }return myDataBase;
    }

}
