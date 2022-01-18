package com.example.noteapp.ui;

import android.content.res.Configuration;  // pull requests
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.noteapp.R;
import com.example.noteapp.data.Constants;
import com.example.noteapp.data.InMemoryRepoImpl;
import com.example.noteapp.data.Note;
import com.example.noteapp.data.Repo;


public class EditNoteFragment extends Fragment implements View.OnClickListener {

    private Repo repository = InMemoryRepoImpl.getInstance();
    private EditText title;
    private EditText description;
    private Button saveNote;
    private Integer id = -1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit_note, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        title = view.findViewById(R.id.edit_note_title);
        description = view.findViewById(R.id.edit_note_description);
        saveNote = view.findViewById(R.id.edit_note_update);
        saveNote.setOnClickListener(this);
        Bundle arguments = getArguments();
        if (arguments != null) {
            Note note = (Note) arguments.getSerializable(Constants.NOTE);
            id = note.getId();
            title.setText(note.getTitle());
            description.setText(note.getDescription());
        }

        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        if(id == null || id == -1){
            repository.create(new Note(title.getText().toString(), description.getText().toString()));
        }else {
            Note note = new Note(id, title.getText().toString(), description.getText().toString());
            repository.update(note);
        }
        if (getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE) {
            ((NoteListFragment) requireActivity().getSupportFragmentManager()
                    .findFragmentById(R.id.list_fragment))
                    .updateList();
        }else{
            requireActivity().getSupportFragmentManager().popBackStack();
        }
    }

    public static EditNoteFragment newInstance(Note note) {

        EditNoteFragment fragment = new EditNoteFragment();
        Bundle args = new Bundle();
        args.putSerializable(Constants.NOTE, note);
        fragment.setArguments(args);
        return fragment;
    }

}