package com.example.nayantiwari.todoapplication.data;

import android.provider.BaseColumns;

/**
 * Created by nayantiwari on 7/3/17.
 */

public class ToDoListContract {

    public static final class ToDoListEntry implements BaseColumns {

        public static final String TABLE_NAME = "todolist";
        public static final String COLUMN_TITLE = "guestName";
        public static final String COLUMN_TODO_DATE = "partySize";
        public static final String COLUMN_TODO_TIME = "timestamp";
        public static final String COLUMN_IS_REMINDER = "reminder";

    }


}
