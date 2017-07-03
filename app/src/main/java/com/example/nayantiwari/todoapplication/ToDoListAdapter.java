package com.example.nayantiwari.todoapplication;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ToDoListViewHolder extends RecyclerView.ViewHolder {

        TextView titleTextView;
        TextView dateTimeTextView;

        public ToDoListViewHolder(View itemView) {
            super(itemView);

            titleTextView = (TextView) itemView.findViewById(R.id.title_tv);
            dateTimeTextView = (TextView) itemView.findViewById(R.id.date_time_tv);
        }
    }
}
