package com.example.testingfragments;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;

import java.util.List;

public class NotesRepository {

    private
    NotesDAO noteDao;
    private LiveData<List<Notes>> allNotes;

    public NotesRepository(Application application){
        NotesDatabase database = NotesDatabase.getInstance(application);

        noteDao = database.notesDAO();
        allNotes = noteDao.getAllNotes();
    }
    public void insert(Notes notes){

        new InsertNotesAsyncTask(noteDao).execute(notes);
    }
    public void update(Notes notes){
        new UpdateNotesAsyncTask(noteDao).execute(notes);
    }
    public void delete(Notes notes){
        new DeleteNotesAsyncTask(noteDao).execute(notes);
    }
    public void deleteAllNotes()
    {
        new DeleteAllNotesAsyncTask(noteDao).execute();
    }
    public LiveData<List<Notes>> getAllNotes() {
        return allNotes;
    }

    //Insert Notes AsyncTask.
    private static class InsertNotesAsyncTask extends AsyncTask<Notes , Void , Void>{
        private NotesDAO notesDAO;

        private InsertNotesAsyncTask(NotesDAO notesDAO){
            this.notesDAO=notesDAO;
        }

        @Override
        protected Void doInBackground(Notes... notes) {
            notesDAO.insert(notes[0]);
            return null;
        }
    }

    //Update Notes AsyncTask
    private static class UpdateNotesAsyncTask extends AsyncTask<Notes , Void , Void>{
        private NotesDAO notesDAO;

        private UpdateNotesAsyncTask(NotesDAO notesDAO){
            this.notesDAO=notesDAO;
        }

        @Override
        protected Void doInBackground(Notes... notes) {
            notesDAO.update(notes[0]);
            return null;
        }
    }

    //Delete Notes Asynctask
    private static class DeleteNotesAsyncTask extends AsyncTask<Notes , Void , Void>{
        private NotesDAO notesDAO;

        private DeleteNotesAsyncTask(NotesDAO notesDAO){
            this.notesDAO=notesDAO;
        }

        @Override
        protected Void doInBackground(Notes... notes) {
            notesDAO.delete(notes[0]);
            return null;
        }
    }

    //DeleteAll notes Aysnctask
    private static class DeleteAllNotesAsyncTask extends AsyncTask<Notes , Void , Void>{
        private NotesDAO notesDAO;

        private DeleteAllNotesAsyncTask(NotesDAO notesDAO){
            this.notesDAO=notesDAO;
        }

        @Override
        protected Void doInBackground(Notes... notes) {
            notesDAO.deleteAllNotes();
            return null;
        }
    }

}
