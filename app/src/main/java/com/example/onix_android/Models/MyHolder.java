package com.example.onix_android.Models;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onix_android.Interfaces.ItemClickListener;
import com.example.onix_android.R;

public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView title, dateCreate, tag, text;
    LinearLayout color;
    ItemClickListener itemClickListener;

    public MyHolder(@NonNull View itemView) {
        super(itemView);

        this.text = itemView.findViewById(R.id.text);
        this.title = itemView.findViewById(R.id.title);
        this.tag = itemView.findViewById(R.id.tag);
        this.color = itemView.findViewById(R.id.to_change_color);
        this.dateCreate = itemView.findViewById(R.id.date_created);

        itemView.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        this.itemClickListener.onClickListener(v, getLayoutPosition());
    }

    public void setItemClickListener(ItemClickListener ic) {
        this.itemClickListener = ic;
    }
}
