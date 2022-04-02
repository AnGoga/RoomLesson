package com.angogasapps.roomlesson;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = { Note.class }, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    abstract NoteDao getNoteDao();
}
