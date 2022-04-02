package com.angogasapps.roomlesson;

import android.content.Context;

import androidx.room.Room;

public class DatabaseManager {
    private static AppDatabase database;

    private DatabaseManager() {
    }

    public static AppDatabase getInstance(Context context) {
        synchronized (DatabaseManager.class) {
            if (database == null) {
                database = Room.databaseBuilder(context, AppDatabase.class, "mydatabase.db").build();
            }
            return database;
        }
    }
}
