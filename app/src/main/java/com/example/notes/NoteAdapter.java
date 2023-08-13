package com.example.notes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.concurrent.TimeoutException;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ListItemHolder> {

    private List<Note> mNoteList;
    private MainActivity mMainActivity;

    @NonNull
    @Override
    public NoteAdapter.ListItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem, parent, false);
        return new ListItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.ListItemHolder holder, int position) {

        Note note = mNoteList.get(position);
        holder.mTitle.setText(note.getTitle());
        if (note.getDescription().length() > 15) {
            holder.mDescription.setText(note.getDescription().substring(0, 15));
        } else {
            holder.mDescription.setText(note.getDescription().substring(0, note.getDescription().length()));
        }

        if (note.isIdea()) {
            holder.mStatus.setText(R.string.idea_text);
        } else if (note.isTodo()) {
            holder.mStatus.setText(R.string.todo_text);
        } else {
            holder.mStatus.setText(R.string.important_text);
        }
    }

    @Override
    public int getItemCount() {
        return mNoteList.size();
    }

    public NoteAdapter(MainActivity mainActivity, List<Note> noteList) {

        mMainActivity = mainActivity;
        mNoteList = noteList;
    }

    public class ListItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView mTitle;
        TextView mDescription;
        TextView mStatus;

        public ListItemHolder(View view) {
            super(view);
            mTitle = view.findViewById(R.id.textViewTitle);
            mDescription = view.findViewById(R.id.textViewDescription);
            mStatus = view.findViewById(R.id.textViewStatus);
        }

        @Override
        public void onClick(View view) {
            mMainActivity.showNote(getAdapterPosition());
        }
    }
}
