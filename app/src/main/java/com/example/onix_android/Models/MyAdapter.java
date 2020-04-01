package com.example.onix_android.Models;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onix_android.FullScreenNote;
import com.example.onix_android.Interfaces.ItemClickListener;
import com.example.onix_android.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MyAdapter extends RecyclerView.Adapter<MyHolder> {

    Context c;
    ArrayList<Note> notes;

    public MyAdapter(Context c, ArrayList<Note> notes) {
        this.c = c;
        this.notes = notes;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int i) {
        holder.title.setText(notes.get(i).getTitle());
        holder.tag.setText(notes.get(i).getTag());
        holder.text.setText(notes.get(i).getText());
        holder.color.setBackgroundColor(Color.parseColor(
                "#" + Integer.toHexString(notes.get(i).getColor())));
        holder.dateCreate.setText(new SimpleDateFormat(
                "dd.MM.yyyy", Locale.getDefault()).format(new Date()));

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClickListener(View v, int position) {
                String title = notes.get(position).getTitle();
                String tag = notes.get(position).getTag();
                String text = notes.get(position).getText();
                String date = new SimpleDateFormat(
                        "dd.MM.yyyy", Locale.getDefault()).format(new Date());

                Intent intent = new Intent(c, FullScreenNote.class);
                intent.putExtra("title", title);
                intent.putExtra("tag", tag);
                intent.putExtra("text", text);
                intent.putExtra("date", date);
                c.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }
}
