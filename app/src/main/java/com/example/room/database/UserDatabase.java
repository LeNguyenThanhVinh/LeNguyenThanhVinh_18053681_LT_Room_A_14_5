package com.example.room.database;

import android.content.Context;
import android.view.View;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.room.MainActivity;
import com.example.room.User;

@Database(entities = {User.class}, version = 1)
public abstract class UserDatabase extends RoomDatabase {
    public static final String DATABASE_NAME = "user.db";
    private static UserDatabase instance;
    public static synchronized UserDatabase getInstance(MainActivity context){
        if(instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext(),UserDatabase.class,DATABASE_NAME)
                    .allowMainThreadQueries().build();
        }
        return instance;
    }
    public abstract UserDAO userDAO ();
}
