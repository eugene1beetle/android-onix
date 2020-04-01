package com.example.onix_android.Models;

import java.util.Arrays;

public class Note {

    private String title, text, tag;
    private int color;

    public Note() {
        this.title = "Hey!";
        this.tag = "Default";
        this.text = "Default";
        this.color = 0x12345678;
    }
    public Note(String title, String text, String tag, int color) {
        this.title = title;
        this.text = text;
        this.tag = tag;
        this.color = color;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
