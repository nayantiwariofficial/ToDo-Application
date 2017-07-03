package com.example.nayantiwari.todoapplication;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.icu.util.Calendar;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.nayantiwari.todoapplication.data.ToDoListContract;

import java.util.Date;

public class AddNewToDoActivity extends AppCompatActivity {

    private SQLiteDatabase mDb;

    private int year;
    private int month;
    private int day;

    private int hour;
    private int min;
    String amPm;

    EditText mDate;
    EditText mTime;

    String selectedDate, selectedTime;

    Date mUserReminderDate;

    Switch aSwitch;

    EditText enterToDoEditText;

    FloatingActionButton floatingActionButton;

    TextView mTimeDateMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_to_do);

        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab2);

        mDate = (EditText) findViewById(R.id.date_et);
        mTime = (EditText) findViewById(R.id.time_et);

        aSwitch = (Switch) findViewById(R.id.switch1);

        mTimeDateMessage = (TextView) findViewById(R.id.date_time_tv);

        enterToDoEditText = (EditText) findViewById(R.id.enter_todo_et);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_black_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDate.setOnClickListener(new View.OnClickListener() {
            public static final String TAG = "OnClickListener";

            @Override
            public void onClick(View v) {
//                hideKeyboard(enterToDoEditText);
//                hideKeyboard(mDate);
                Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DATE);

                DatePickerDialog datePickerDialog = new DatePickerDialog(AddNewToDoActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public static final String TAG = "DatePickerDialog";

                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Log.i(TAG, "onDateSet: " + year + " " + (month + 1) + " " + dayOfMonth);
                        selectedDate = (month + 1) + "/" + dayOfMonth + "/" + year;
                        mDate.setText(selectedDate);
                    }
                }, year, month, day);

                datePickerDialog.show();
            }
        });

        mTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                hideKeyboard(mTime);
                Calendar calendar = Calendar.getInstance();

                hour = calendar.get(Calendar.HOUR_OF_DAY);
                min = calendar.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(AddNewToDoActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    public static final String TAG = "TimePickerDialog";

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        if (hourOfDay > 12) {
                            amPm = "PM";
                            hour = hourOfDay - 12;
                        } else {
                            amPm = "AM";
                            hour = hourOfDay;
                        }
                        Log.i(TAG, "onTimeSet: " + hour + ":" + minute + " " + amPm);
                        if (minute < 10 && hour > 10) {
                            selectedTime = hour + ":0" + minute + " " + amPm;
                        } else if (hour < 10 && minute > 10) {
                            selectedTime = "0" + hour + ":" + minute + " " + amPm;
                        } else if (hour < 10 && minute < 10) {
                            selectedTime = "0" + hour + ":0" + minute + " " + amPm;
                        } else
                            selectedTime = hour + ":" + minute + " " + amPm;

                        mTime.setText(selectedTime);
                    }
                }, hour, min, false);
                timePickerDialog.show();
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aSwitch.isChecked()) {
                    setReminderAndToDo();
                } else {
                    setToDoWithoutReminder();
                }
            }
        });
    }


    public void setReminderAndToDo() {
        if (selectedDate != null && selectedTime != null) {
            String finalMessage;
            finalMessage = "Reminder set for " + selectedDate + ", " + selectedTime;
            mTimeDateMessage.setText(finalMessage);

            addNewToDo(enterToDoEditText.getText().toString(), selectedDate, selectedTime, aSwitch.isChecked());


        } else if (selectedTime == null && selectedDate != null) {
            Toast.makeText(this, "Please enter time", Toast.LENGTH_SHORT).show();
        } else if (selectedTime != null)
        {
            if(selectedDate == null)
            Toast.makeText(this, "Please enter the date", Toast.LENGTH_SHORT).show();
        } else if (selectedTime == null && selectedDate == null)
            addNewToDo(enterToDoEditText.getText().toString(), null, null, false);
    }

    private long addNewToDo(String title, String selectedDate, String selectedTime, boolean checked) {
        ContentValues cv = new ContentValues();
        cv.put(ToDoListContract.ToDoListEntry.COLUMN_TITLE, title);
        cv.put(ToDoListContract.ToDoListEntry.COLUMN_TODO_DATE, selectedDate);
        cv.put(ToDoListContract.ToDoListEntry.COLUMN_TODO_TIME, selectedTime);
        cv.put(ToDoListContract.ToDoListEntry.COLUMN_IS_REMINDER, checked);

        return mDb.insert(ToDoListContract.ToDoListEntry.TABLE_NAME, null, cv);
    }

    public void setToDoWithoutReminder() {
        Toast.makeText(this, "Todo Saved without setting reminder", Toast.LENGTH_SHORT).show();
    }

//    void hideKeyboard(EditText editText) {
//        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
//        inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
//    }
}
