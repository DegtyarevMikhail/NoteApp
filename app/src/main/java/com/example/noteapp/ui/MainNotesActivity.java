package com.example.noteapp.ui;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.noteapp.R;
import com.example.noteapp.data.InMemoryRepoImpl;
import com.example.noteapp.data.Note;
import com.example.noteapp.data.Repo;


public class MainNotesActivity extends AppCompatActivity {

    private Repo repository = InMemoryRepoImpl.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);
        if (savedInstanceState == null) {
            fillRepo();
        }
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.list_fragment, new NoteListFragment());
        fragmentTransaction.addToBackStack("");
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();

        if (getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE) {
            onNoteClickLand(new Note("",""));
        }
    }


/*

   @Override
    protected void onResume() {
        super.onResume();
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

 */

    private void fillRepo() {
        repository.create(new Note("NOTE 1", "DESCRIPTION 1 "));
        repository.create(new Note("NOTE 2", "DESCRIPTION 2 "));
        repository.create(new Note("NOTE 3", "DESCRIPTION 3 "));
        repository.create(new Note("NOTE 4", "DESCRIPTION 4 "));
        repository.create(new Note("NOTE 5", "DESCRIPTION 5 "));
        repository.create(new Note("NOTE 6", "DESCRIPTION 6 "));
        repository.create(new Note("NOTE 7", "DESCRIPTION 7 "));
        repository.create(new Note("NOTE 8", "DESCRIPTION 8 "));
        repository.create(new Note("NOTE 9", "DESCRIPTION 9 "));
        repository.create(new Note("NOTE 10", "DESCRIPTION 10 "));
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main_create:
                if (getResources().getConfiguration().orientation
                        == Configuration.ORIENTATION_LANDSCAPE) {
                    onNoteClickLand(new Note("",""));
                } else {
                    onNoteClickPort(new Note("",""));
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void onNoteClickPort(Note note) {
        EditNoteFragment detail = EditNoteFragment.newInstance(note);
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.list_fragment, detail);
        fragmentTransaction.addToBackStack("");
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }

    private void onNoteClickLand(Note note) {
        EditNoteFragment detail = EditNoteFragment.newInstance(note);
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.edit_fragment, detail);
        fragmentTransaction.addToBackStack("");
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }
}
