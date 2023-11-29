package com.example.musicapp;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class ListItem {

    private String textView1Text;
    private String button;
    private String data;


    public ListItem(String data,String buttonName, String tittle) {

        this.textView1Text = tittle;
        this.button = buttonName;
        this.data = data;

    }

    public String getTextView1Text() {
        return textView1Text;
    }

    public String getButton(){return button;}

    public String getData(){return data;}

}

