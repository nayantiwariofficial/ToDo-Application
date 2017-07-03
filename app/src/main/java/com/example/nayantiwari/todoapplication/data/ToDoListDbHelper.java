package com.example.nayantiwari.todoapplication.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.R.attr.version;

import com.example.nayantiwari.todoapplication.data.ToDoListContract.ToDoListEntry;

/**
 * Created by nayantiwari on 7/3/17.
 */

public class ToDoListDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "todolist.db";

    public static final int DATABASE_VERSION = 1;

    public ToDoListDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String SQL_CREATE_TODOLIST_TABLE = "CREATE TABLE " + ToDoListEntry.TABLE_NAME + "(" +
                ToDoListEntry._ID + "INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ToDoListEntry.COLUMN_TITLE + "TEXT NOT NULL, " +
                ToDoListEntry.COLUMN_TODO_DATE + "TEXT, " +
                ToDoListEntry.COLUMN_TODO_TIME + "TEXT, " +
                ToDoListEntry.COLUMN_IS_REMINDER + "INTEGER NOT NULL " +
                ");" ;

        db.execSQL(SQL_CREATE_TODOLIST_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ToDoListEntry.TABLE_NAME);
        onCreate(db);
    }
}
