package com.angogasapps.roomlesson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button button;
    EditText titleInput, textInput;
    AppDatabase database;
    RecyclerView recyclerView;
    NoteAdapter adapter = new NoteAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = DatabaseManager.getInstance(this);

        button = findViewById(R.id.button);
        titleInput = findViewById(R.id.title);
        textInput = findViewById(R.id.text);
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        button.setOnClickListener(v -> {
            String title = titleInput.getText().toString();
            String text = textInput.getText().toString();
            Note note = new Note(title, text);
            new Thread() {
                @Override
                public void run() {
                    database.getNoteDao().insertNote(note);
                }
            }.start();
            adapter.addNote(note);
        });

        fillRecycler();
    }

    void fillRecycler() {
        new Thread() {
            @Override
            public void run() {
                List<Note> list = database.getNoteDao().getAll();
                runOnUiThread(() -> {
                    for (Note note : list) {
                        adapter.addNote(note);
                    }
                });
            }
        }.start();
    }
}
