package com.example.musicapp;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.BLUE;
import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;
import static android.graphics.Color.WHITE;
import static android.graphics.Color.YELLOW;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.IOException;

public class MusicActivity extends AppCompatActivity {
    ToggleButton btn_play,btn_theme,btn_magic;
    Button btn_previous,btn_left_speed,btn_right_speed;
    Button btn_next;
    MediaPlayer mediaPlayer;
    Cursor cursor;
    TextView txt_m_name;

    SeekBar seekBar;

    ImageView img_queue,img_main_img;

    LinearLayout backgrounds;
    Animation animation;

    String filepath;

    String data;

    private final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        btn_play = findViewById(R.id.btn_play);
        btn_previous = findViewById(R.id.btn_backword);
        btn_next = findViewById(R.id.btn_forward);
        mediaPlayer = new MediaPlayer();
        txt_m_name = findViewById(R.id.txt_m_name);
        seekBar = findViewById(R.id.seekBar);
        img_queue = findViewById(R.id.queue_traverce);
        btn_theme = findViewById(R.id.btn_theme);
        backgrounds = findViewById(R.id.backgrounds);
        img_main_img = findViewById(R.id.img_main_back);
        btn_magic = findViewById(R.id.btn_magic);
        btn_left_speed = findViewById(R.id.btn_left_speed);
        btn_right_speed = findViewById(R.id.btn_right_speed);




        btn_right_speed.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (cursor != null && cursor.moveToLast()) {
                    int filePathIndex = cursor.getColumnIndex(MediaStore.Audio.Media.DATA);
                    filepath = cursor.getString(filePathIndex);
                    int t = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
                    txt_m_name.setText(cursor.getString(t));

                    try {
                        mediaPlayer.reset();
                        mediaPlayer.setDataSource(filepath);
                        mediaPlayer.prepare();
                        mediaPlayer.start();
                        updateSeekBar();
                        if(btn_theme.isChecked()){
                            btn_play.setBackgroundResource(R.drawable.baseline_pause_24_1);
                        }
                        else{
                            btn_play.setBackgroundResource(R.drawable.baseline_pause_24);
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {

                }
                return true;
            }
        });

        btn_left_speed.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (cursor != null && cursor.moveToFirst()) {
                    int filePathIndex = cursor.getColumnIndex(MediaStore.Audio.Media.DATA);
                    filepath = cursor.getString(filePathIndex);
                    int t = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
                    txt_m_name.setText(cursor.getString(t));

                    try {
                        mediaPlayer.reset();
                        mediaPlayer.setDataSource(filepath);
                        mediaPlayer.prepare();
                        mediaPlayer.start();
                        updateSeekBar();
                        if(btn_theme.isChecked()){
                            btn_play.setBackgroundResource(R.drawable.baseline_pause_24_1);
                        }
                        else{
                            btn_play.setBackgroundResource(R.drawable.baseline_pause_24);
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {

                }
                return true;
            }
        });

        btn_magic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btn_magic.isChecked()){
                    final CharSequence[] color = {"RED","GREEN","BLUE","YELLOW"};

                    AlertDialog.Builder alert = new AlertDialog.Builder(MusicActivity.this,R.style.chooses);

                    alert.setTitle("LEENA MUSIC"+"\n\n\n"+"choose the color");

                    alert.setSingleChoiceItems(color, -1, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if(color[which]=="RED")
                            {
                                btn_theme.setVisibility(View.INVISIBLE);
                                animation = new AlphaAnimation(0.0f,1.0f);
                                animation.setDuration(50);
                                animation.setStartOffset(20);
                                backgrounds.setBackgroundColor(RED);
                                animation.setRepeatMode(Animation.REVERSE);
                                animation.setRepeatCount(Animation.INFINITE);
                                backgrounds.startAnimation(animation);
                            }
                            else if (color[which]=="GREEN")
                            {
                                btn_theme.setVisibility(View.INVISIBLE);
                                animation = new AlphaAnimation(0.0f,1.0f);
                                animation.setDuration(50);
                                animation.setStartOffset(20);
                                backgrounds.setBackgroundColor(GREEN);
                                animation.setRepeatMode(Animation.REVERSE);
                                animation.setRepeatCount(Animation.INFINITE);
                                backgrounds.startAnimation(animation);
                            }
                            else if (color[which]=="BLUE")
                            {
                                btn_theme.setVisibility(View.INVISIBLE);
                                animation = new AlphaAnimation(0.0f,1.0f);
                                animation.setDuration(50);
                                animation.setStartOffset(20);
                                backgrounds.setBackgroundColor(BLUE);
                                animation.setRepeatMode(Animation.REVERSE);
                                animation.setRepeatCount(Animation.INFINITE);
                                backgrounds.startAnimation(animation);

                            }
                            else if (color[which]=="YELLOW")
                            {
                                btn_theme.setVisibility(View.INVISIBLE);
                                animation = new AlphaAnimation(0.0f,1.0f);
                                animation.setDuration(50);
                                animation.setStartOffset(20);
                                backgrounds.setBackgroundColor(YELLOW);
                                animation.setRepeatMode(Animation.REVERSE);
                                animation.setRepeatCount(Animation.INFINITE);
                                backgrounds.startAnimation(animation);
                            }
                        }
                    }).show();
                }
                else{
                    animation.cancel();
                    btn_theme.setVisibility(View.VISIBLE);
                    if(btn_theme.isChecked()){
                        backgrounds.setBackgroundColor(BLACK);
                    }
                    else{
                        backgrounds.setBackgroundColor(WHITE);
                    }

                }
            }
        });

        btn_theme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btn_theme.isChecked()){
                    backgrounds.setBackgroundColor(BLACK);
                    if(mediaPlayer.isPlaying()){
                       btn_play.setBackgroundResource(R.drawable.baseline_pause_24_1);
                    }
                    else{
                        btn_play.setBackgroundResource(R.drawable.baseline_play_arrow_24_1);
                    }
                    btn_magic.setBackgroundResource(R.drawable.baseline_auto_fix_high_24_1);
                    btn_next.setBackgroundResource(R.drawable.baseline_arrow_right_24_1);
                    btn_previous.setBackgroundResource(R.drawable.baseline_arrow_left_24_1);
                    btn_left_speed.setBackgroundResource(R.drawable.baseline_keyboard_double_arrow_left_24_1);
                    btn_right_speed.setBackgroundResource(R.drawable.baseline_keyboard_double_arrow_right_24_1);
                    img_main_img.setImageResource(R.drawable.baseline_music_note_24_1);
                    txt_m_name.setTextColor(WHITE);
                    btn_theme.setBackgroundResource(R.drawable.baseline_nightlight_24);

                }
                else{
                    backgrounds.setBackgroundColor(WHITE);
                    if(mediaPlayer.isPlaying()){
                        btn_play.setBackgroundResource(R.drawable.baseline_pause_24);
                    }
                    else{
                        btn_play.setBackgroundResource(R.drawable.baseline_play_arrow_24);
                    }
                    btn_magic.setBackgroundResource(R.drawable.baseline_auto_fix_high_24);
                    btn_next.setBackgroundResource(R.drawable.baseline_arrow_right_24);
                    btn_previous.setBackgroundResource(R.drawable.baseline_arrow_left_24);
                    btn_left_speed.setBackgroundResource(R.drawable.baseline_keyboard_double_arrow_left_24);
                    btn_right_speed.setBackgroundResource(R.drawable.baseline_keyboard_double_arrow_right_24);
                    img_main_img.setImageResource(R.drawable.baseline_music_note_24);
                    txt_m_name.setTextColor(BLACK);
                    btn_theme.setBackgroundResource(R.drawable.baseline_brightness_high_24);
                }

            }
        });

        img_queue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data = null;
                Intent intent = new Intent(MusicActivity.this,QueueActivity.class);
                startActivity(intent);

            }
        });


        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

        String[] projection = {
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.DURATION
        };

        cursor = getContentResolver().query(uri, projection, null, null, null);



        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    if (btn_play.isChecked()) {
                        if(btn_theme.isChecked()){
                            btn_play.setBackgroundResource(R.drawable.baseline_pause_24_1);
                        }
                        else{
                            btn_play.setBackgroundResource(R.drawable.baseline_pause_24);
                        }
                        if (!mediaPlayer.isPlaying()) {
                            try {
                                playCurrentSong();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                           // mediaPlayer.start();
                            updateSeekBar();
                        }
                    } else {
                        mediaPlayer.pause();
                        if(btn_theme.isChecked()){
                            btn_play.setBackgroundResource(R.drawable.baseline_play_arrow_24_1);
                        }
                        else{
                            btn_play.setBackgroundResource(R.drawable.baseline_play_arrow_24);
                        }
                    }

            }
        });

        btn_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cursor != null && cursor.moveToPrevious()) {
                    int filePathIndex = cursor.getColumnIndex(MediaStore.Audio.Media.DATA);
                    filepath = cursor.getString(filePathIndex);
                    int t = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
                    txt_m_name.setText(cursor.getString(t));

                    try {
                        mediaPlayer.reset();
                        mediaPlayer.setDataSource(filepath);
                        mediaPlayer.prepare();
                        mediaPlayer.start();
                        updateSeekBar();
                        if(btn_theme.isChecked()){
                            btn_play.setBackgroundResource(R.drawable.baseline_pause_24_1);
                        }
                        else{
                            btn_play.setBackgroundResource(R.drawable.baseline_pause_24);
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {

                }
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (cursor != null && cursor.moveToNext()) {
                    int filePathIndex = cursor.getColumnIndex(MediaStore.Audio.Media.DATA);
                    filepath = cursor.getString(filePathIndex);
                    int t = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
                    txt_m_name.setText(cursor.getString(t));

                    try {
                        mediaPlayer.reset();
                        mediaPlayer.setDataSource(filepath);
                        mediaPlayer.prepare();
                        mediaPlayer.start();
                        updateSeekBar();
                        if(btn_theme.isChecked()){
                            btn_play.setBackgroundResource(R.drawable.baseline_pause_24_1);
                        }
                        else{
                            btn_play.setBackgroundResource(R.drawable.baseline_pause_24);
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {

                }
            }
        });

    }

    private void playCurrentSong() throws IOException {
        if(filepath != null){
            mediaPlayer.reset();
            mediaPlayer.setDataSource(filepath);
            mediaPlayer.prepare();
            mediaPlayer.start();
            if (btn_theme.isChecked()) {
                btn_play.setBackgroundResource(R.drawable.baseline_pause_24_1);
            } else {
                btn_play.setBackgroundResource(R.drawable.baseline_pause_24);
            }
        }
        else {
            if (cursor != null && cursor.moveToFirst()) {
                int filePathIndex = cursor.getColumnIndex(MediaStore.Audio.Media.DATA);
                filepath = cursor.getString(filePathIndex);
                int t = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
                txt_m_name.setText(cursor.getString(t));

                try {
                    mediaPlayer.reset();
                    mediaPlayer.setDataSource(filepath);
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                    if (btn_theme.isChecked()) {
                        btn_play.setBackgroundResource(R.drawable.baseline_pause_24_1);
                    } else {
                        btn_play.setBackgroundResource(R.drawable.baseline_pause_24);
                    }

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }


    private void updateSeekBar() {
        // Update the seek bar's progress based on the current position of the MediaPlayer
        seekBar.setMax(mediaPlayer.getDuration());
        seekBar.setProgress(mediaPlayer.getCurrentPosition());

        // Use the handler to update the seek bar periodically
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                updateSeekBar();
            }
        }, 1000); // Update the seek bar every 1000 milliseconds (1 second)
    }




}


