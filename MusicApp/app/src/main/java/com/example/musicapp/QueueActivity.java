package com.example.musicapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class QueueActivity extends AppCompatActivity {

    SearchView searchView;
    RecyclerView recyclerView;

    List<ListItem> music_queue;

    Cursor cursor;

    CustomAdapter customAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queue);
        searchView = findViewById(R.id.search);
        recyclerView = findViewById(R.id.recycle_view);

        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

        String[] projection = {
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.TITLE,
        };

        cursor = getContentResolver().query(uri, projection, null, null, null);
        music_queue = new ArrayList<>();


        while (cursor.moveToNext()) {
            int titile = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int data = cursor.getColumnIndex(MediaStore.Audio.Media.DATA);
            music_queue.add(new ListItem(cursor.getString(data),"PLAY",cursor.getString(titile)));

        }

        customAdapter = new CustomAdapter(this, music_queue);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(customAdapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                customAdapter.getFilter().filter(newText);
                return true;
            }
        });


    }
}