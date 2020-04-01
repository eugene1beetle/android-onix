package com.example.onix_android;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.onix_android.Models.MyAdapter;
import com.example.onix_android.Models.Note;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class MainFrame extends AppCompatActivity {

    RecyclerView recyclerView;
    MyAdapter myAdapter;
    ArrayList<Note> notes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_frame);

        addListeners();
        createRecyclerView();
    }

    private void createRecyclerView() {
        recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainFrame.this));
        myAdapter = new MyAdapter(MainFrame.this, getStartList());
        recyclerView.setAdapter(myAdapter);
    }

    private void updateRecyclerView() {
        myAdapter = new MyAdapter(MainFrame.this, notes);
        recyclerView.setAdapter(myAdapter);
    }

    private ArrayList<Note> getStartList() {
        Note n = new Note();
        n.setColor(0x48618a);
        n.setTag("TAG!");
        n.setTitle("Great days!");
        n.setText("I could not associate this program with firebase for some reason, because of" +
                " this I could not complete the task normally");
        notes.add(n);
        return notes;
    }

    private void addListeners() {
        addFBtnListener();
    }

    private void addFBtnListener() {
        findViewById(R.id.fab_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewNoteFrame();
            }
        });
    }

    private void createNewNoteFrame() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(MainFrame.this);
        dialog.setTitle("New note");
        dialog.setCancelable(true);

        LayoutInflater layoutInflater = LayoutInflater.from(MainFrame.this);
        View newNoteWindow = layoutInflater.inflate(R.layout.new_note_window, null);
        dialog.setView(newNoteWindow);
        dialog.setCancelable(true);

        Button addNoteBtn = newNoteWindow.findViewById(R.id.create_note);
        final TextInputEditText title = newNoteWindow.findViewById(R.id.n_title);
        final TextInputEditText text = newNoteWindow.findViewById(R.id.n_text);
        final TextInputEditText tag = newNoteWindow.findViewById(R.id.n_tag);

        dialog.show();

        addBtnInWindowListener(addNoteBtn, title, text, tag);
    }

    private void addBtnInWindowListener(Button addNoteBtn, final TextInputEditText title, final TextInputEditText text, final TextInputEditText tag) {
        addNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (title.getText().toString().equals("")) {
                    Toast.makeText(MainFrame.this, "Fill title, please",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                if (text.getText().toString().equals("")) {
                    Toast.makeText(MainFrame.this, "Fill title, please",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                Note note = new Note(
                    title.getText().toString(),
                    text.getText().toString(),
                    tag.getText().toString(),
                    0x12345678
                );

                notes.add(note);
                updateRecyclerView();

                Toast.makeText(MainFrame.this, "Note added", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
