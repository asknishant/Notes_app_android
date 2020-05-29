package com.example.testingfragments;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NotesDAO {

    @Insert
    void insert(Notes notes);

    @Update
    void update(Notes notes);

    @Delete
    void delete(Notes notes);

    //Delete all notes at once.
    @Query("DELETE FROM note_table")
    void deleteAllNotes();

    @Query("SELECT * FROM NOTE_TABLE ORDER BY priority DESC")
    LiveData<List<Notes>> getAllNotes();

}
