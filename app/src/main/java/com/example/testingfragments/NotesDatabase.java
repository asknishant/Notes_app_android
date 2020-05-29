package com.example.testingfragments;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities ={Notes.class}, version = 1)
public abstract class NotesDatabase extends RoomDatabase {

    private static NotesDatabase instance;

    public abstract NotesDAO notesDAO();

    public static synchronized NotesDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NotesDatabase.class,"note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsynctask(instance).execute();
        }
    };

    private static class PopulateDbAsynctask extends AsyncTask<Void, Void, Void> {
        private NotesDAO notesDAO;
        private  PopulateDbAsynctask(NotesDatabase db){
            notesDAO = db.notesDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            notesDAO.insert(new Notes("Title 1","Description 1",1));
            notesDAO.insert(new Notes("Title 2","Description 2",2));
            notesDAO.insert(new Notes("Title 3","Description 3",3));
            return  null;
        }
    }

}
