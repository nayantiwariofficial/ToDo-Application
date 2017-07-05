package com.example.nayantiwari.todoapplication;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nayantiwari.todoapplication.data.ToDoListContract;

import static android.R.attr.name;

/**
 * Created by nayantiwari on 7/3/17.
 */

public class ToDoListAdapter extends RecyclerView.Adapter<ToDoListAdapter.ToDoListViewHolder> {

    private Cursor mCursor;
    private Context mContext;

    public ToDoListAdapter(Cursor mCursor, Context mContext) {
        this.mCursor = mCursor;
        this.mContext = mContext;
    }

    @Override
    public ToDoListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.todo_list_item, parent, false);
        return new ToDoListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ToDoListViewHolder holder, int position) {
        if (!mCursor.moveToPosition(position))
            return; // bail if returned null

        // Update the view holder with the information needed to display
        String title = mCursor.getString(mCursor.getColumnIndex(ToDoListContract.ToDoListEntry.COLUMN_TITLE));
        int isReminder = mCursor.getInt(mCursor.getColumnIndex(ToDoListContract.ToDoListEntry.COLUMN_IS_REMINDER));
        String date = mCursor.getString(mCursor.getColumnIndex(ToDoListContract.ToDoListEntry.COLUMN_TODO_DATE));
        String time = mCursor.getString(mCursor.getColumnIndex(ToDoListContract.ToDoListEntry.COLUMN_TODO_TIME));
        String dateTime = date + " " + time;
        long id = mCursor.getLong(mCursor.getColumnIndex(ToDoListContract.ToDoListEntry._ID));
        holder.titleTextView.setText(title);
        String firstLetter = title.charAt(0) + "";
        holder.initialLetterTextView.setAllCaps(true);
        holder.initialLetterTextView.setText(firstLetter);
        // Display the date and time
        if (isReminder == 1) {
            holder.titleTextView.setMaxLines(1);
            holder.dateTimeTextView.setText(dateTime);
        } else {
            holder.dateTimeTextView.setVisibility(View.GONE);
            holder.titleTextView.setMaxLines(2);
        }
        holder.itemView.setTag(id);

    }

    public void swapCursor(Cursor newCursor) {
        // Always close the previous mCursor first
        if (mCursor != null) mCursor.close();
        mCursor = newCursor;
        if (newCursor != null) {
            // Force the RecyclerView to refresh
            this.notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    class ToDoListViewHolder extends RecyclerView.ViewHolder {

        TextView titleTextView;
        TextView dateTimeTextView;
        TextView initialLetterTextView;

        public ToDoListViewHolder(View itemView) {
            super(itemView);
            titleTextView = (TextView) itemView.findViewById(R.id.title_tv);
            dateTimeTextView = (TextView) itemView.findViewById(R.id.date_time_info_tv);
            initialLetterTextView = (TextView) itemView.findViewById(R.id.initial_letter_circle);
        }
    }
}
