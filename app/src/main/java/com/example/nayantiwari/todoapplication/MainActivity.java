package com.example.nayantiwari.todoapplication;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.nayantiwari.todoapplication.data.ToDoListDbHelper;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase mDb;
    ToDoListAdapter toDoListAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) this.findViewById(R.id.all_todo_list_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ToDoListDbHelper dbHelper = new ToDoListDbHelper(this);
        mDb = dbHelper.getWritableDatabase();

        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddNewToDoActivity.class);
                startActivity(intent);
            }
        });
    }

    //TODO continue here


    /*

    private long addNewToDo(String title, String selectedDate, String selectedTime, boolean checked) {
        ContentValues cv = new ContentValues();
        cv.put(ToDoListContract.ToDoListEntry.COLUMN_TITLE, title);
        cv.put(ToDoListContract.ToDoListEntry.COLUMN_TODO_DATE, selectedDate);
        cv.put(ToDoListContract.ToDoListEntry.COLUMN_TODO_TIME, selectedTime);
        cv.put(ToDoListContract.ToDoListEntry.COLUMN_IS_REMINDER, checked);

        return mDb.insert(ToDoListContract.ToDoListEntry.TABLE_NAME, null, cv);
    }

public Cursor getAllToDo() {
        return mDb.query(ToDoListContract.ToDoListEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null);
    }

     */


}
