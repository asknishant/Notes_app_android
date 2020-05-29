package com.example.testingfragments;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NotesViewMModel extends AndroidViewModel {

    private NotesRepository repository;
    private LiveData<List<Notes>> allNotes;

    public NotesViewMModel(@NonNull Application application) {
        super(application);
        repository = new NotesRepository(application);
        allNotes = repository.getAllNotes();
    }

    public void insert(Notes notes){
        repository.insert(notes);
    }
    public void update(Notes notes){
        repository.update(notes);
    }
    public void delete(Notes notes){
        repository.delete(notes);
    }
    public void deleteAllNotes(){
        repository.deleteAllNotes();
    }

    public LiveData<List<Notes>> getAllNotes() {
        return allNotes;
    }
}
